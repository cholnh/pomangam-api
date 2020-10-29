package com.mrporter.pomangam.client.services.order;

import com.mrporter.pomangam._bases.utils.bootpay.model.response.callback.CallbackResponse;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderRequestDto;
import com.mrporter.pomangam.client.domains.order.OrderResponseDto;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.order.bootpay.BootpayVbankDto;
import com.mrporter.pomangam.client.domains.payment.PaymentType;
import com.mrporter.pomangam.client.domains.vbank.VBankReady;
import com.mrporter.pomangam.client.repositories.deliverysite.detail.DeliveryDetailSiteJpaRepository;
import com.mrporter.pomangam.client.repositories.fcm.client.FcmClientTokenJpaRepository;
import com.mrporter.pomangam.client.repositories.order.BootpayVbankJpaRepository;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import com.mrporter.pomangam.client.repositories.user.coupon.CouponJpaRepository;
import com.mrporter.pomangam.client.repositories.user.coupon.CouponMapperJpaRepository;
import com.mrporter.pomangam.client.repositories.vbank.VBankReadyJpaRepository;
import com.mrporter.pomangam.client.services.fcm.FcmServiceImpl;
import com.mrporter.pomangam.client.services.order.exception.OrderException;
import com.mrporter.pomangam.client.services.order.sub_service.*;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    EntityManager em;
    OrderJpaRepository orderRepo;
    CouponJpaRepository couponRepo;
    CouponMapperJpaRepository couponMapperRepo;
    FcmServiceImpl fcmService;
    FcmClientTokenJpaRepository clientTokenRepo;
    DeliveryDetailSiteJpaRepository deliveryDetailSiteRepo;
    UserServiceImpl userService;
    BootpayVbankJpaRepository bootpayVbankRepo;
    VBankReadyJpaRepository vBankReadyRepo;

    CommonSubService commonSubService;
    OrderReadySubService readySubService;
    OrderApproveSubService approveSubService;
    OrderDisapproveSubService disapproveSubService;
    OrderCancelSubService cancelSubService;
    OrderRefundSubService refundSubService;
    DeliveryDelaySubService delaySubService;
    OrderDepositSubService orderDepositSubService;


    @Override
    public List<OrderResponseDto> findAllByIdxFcmToken(Long fIdx, Pageable pageable) {
        return OrderResponseDto.fromEntities(orderRepo.findAllByIdxFcmToken(fIdx, pageable));
    }

    @Override
    public List<OrderResponseDto> findAllByPhoneNumber(String phoneNumber, Pageable pageable) {
        return OrderResponseDto.fromEntities(orderRepo.findAllByPhoneNumber(phoneNumber, pageable));
    }

    @Override
    public List<OrderResponseDto> findTodayByIdxFcmToken(Long fIdx, Pageable pageable) {
        return OrderResponseDto.fromEntities(orderRepo.findTodayByIdxFcmToken(fIdx, pageable));
    }

    @Override
    public List<OrderResponseDto> findTodayByPhoneNumber(String phoneNumber, Pageable pageable) {
        return OrderResponseDto.fromEntities(orderRepo.findTodayByPhoneNumber(phoneNumber, pageable));
    }

    public List<OrderResponseDto> findAllByIdxStore(Long sIdx, Long dIdx, Long ddIdx, Long otIdx, LocalDate oDate, Long last, Pageable pageable) {
        // ddIdx
        if(ddIdx != null) {
            if(otIdx != null) {
                return OrderResponseDto.fromEntities(orderRepo.findAllByIdxStoreAndIdxDetailDeliverySiteAndIdxOrderTime(sIdx, ddIdx, otIdx, oDate, last, pageable));
            } else {
                return OrderResponseDto.fromEntities(orderRepo.findAllByIdxStoreAndIdxDetailDeliverySite(sIdx, ddIdx, oDate, last, pageable));
            }
        }
        // dIdx
        if(dIdx != null) {
            if(otIdx != null) {
                return OrderResponseDto.fromEntities(orderRepo.findAllByIdxStoreAndIdxDeliverySiteAndIdxOrderTime(sIdx, dIdx, otIdx, oDate, last, pageable));
            } else {
                return OrderResponseDto.fromEntities(orderRepo.findAllByIdxStoreAndIdxDeliverySite(sIdx, dIdx, oDate, last, pageable));
            }
        }
        // 전체
        if(otIdx != null) {
            return OrderResponseDto.fromEntities(orderRepo.findAllByIdxStoreAndIdxOrderTime(sIdx, otIdx, oDate, last, pageable));
        } else {
            return OrderResponseDto.fromEntities(orderRepo.findAllByIdxStore(sIdx, oDate, last, pageable));
        }
    }

    public long countByIdxFcmToken(Long fIdx) {
        return orderRepo.countByIdxFcmToken(fIdx);
    }

    public long countByPhoneNumber(String phoneNumber) {
        return orderRepo.countByPhoneNumber(phoneNumber);
    }

    @Override
    public OrderResponseDto patchDetailSite(Long oIdx, Long ddIdx) {
        Order order = orderRepo.findByIdxAndIsActiveIsTrue(oIdx)
                .orElseThrow(() -> new OrderException("invalid order index"));
        DeliveryDetailSite detailSite = deliveryDetailSiteRepo.findByIdxAndIsActiveIsTrue(ddIdx);
        order.setDeliveryDetailSite(detailSite);
        return OrderResponseDto.fromEntity(orderRepo.save(order));
    }

    @Override
    public OrderResponseDto save(OrderRequestDto dto) {
        if(dto.getOrderItems() == null || dto.getOrderItems().isEmpty())
            throw new OrderException("empty order items");

        Long idxOrder = _save(dto).getIdx();
        commonSubService.log(idxOrder, OrderType.PAYMENT_READY);
        em.clear(); // 1차 캐시 제거 -> 새로운 entity 받아옴 (이전 saved entity 는 빈 껍데기..)

        Order order = orderRepo.findByIdxAndIsActiveIsTrue(idxOrder)
                .orElseThrow(() -> new OrderException("invalid order save"));

        commonSubService.verifyUsingPoint(order); // 사용 포인트 검증
        commonSubService.verifyUsingCouponCode(order, dto.getUsingCouponCode());
        commonSubService.verifyUsingCoupons(order, dto.getIdxesUsingCoupons()); // 사용 쿠폰 검증
        commonSubService.verifyUsingPromotions(order, dto.getIdxesUsingPromotions()); // 프로모션 검증
        commonSubService.verifySavedPoint(order); // 적립 포인트 검증

        PaymentType paymentType = order.getPaymentInfo().getPaymentType();
        if(paymentType == PaymentType.CONTACT_CREDIT_CARD || paymentType == PaymentType.CONTACT_CASH || order.paymentCost() <= 0) {
            readySubService.addCntOrder(order);
            readySubService.sendFcm(order);
            readySubService.sendKakaoAT(order);
            commonSubService.log(order.getIdx(), OrderType.PAYMENT_SUCCESS, OrderType.ORDER_READY);
        }

        if(paymentType == PaymentType.COMMON_V_BANK) {
            vBankReadyRepo.save(VBankReady.builder()
                    .idxOrder(order.getIdx())
                    .name(dto.getVbankName())
                    .input(order.paymentCost())
                    .build());
        }

        order.setPaymentCost(order.paymentCost());
        orderRepo.save(order);
        return OrderResponseDto.fromEntity(order);
    }

    @Override
    public boolean verify(Long oIdx, String receipt_id) {
        Order order = orderRepo.findByIdxAndOrderTypeAndIsActiveIsTrue(oIdx, OrderType.PAYMENT_READY)
                .orElseThrow(() -> new OrderException("invalid order verify."));

        readySubService.verifyIsValidVerifyOrder(order.getOrderType());

        boolean isVerified = readySubService.verifyPG(receipt_id, order.paymentCost());
        if(isVerified) {
            readySubService.addCntOrder(order);
            readySubService.sendFcm(order);
            readySubService.sendKakaoAT(order);

            commonSubService.log(oIdx, OrderType.PAYMENT_SUCCESS, OrderType.ORDER_READY);
        } else {
            commonSubService.rollbackUsingPoint(order);
            commonSubService.rollbackUsingCoupons(order);
            commonSubService.log(oIdx, OrderType.PAYMENT_FAIL);
        }
        postReceipt(order, receipt_id);
        return isVerified;
    }

    @Override
    public BootpayVbankDto getVbank(Long oIdx) {
        return BootpayVbankDto.fromEntity(bootpayVbankRepo.findByIdxOrderAndIsActiveIsTrue(oIdx));
    }

    @Override
    public BootpayVbankDto postVbank(BootpayVbankDto dto) {
        return BootpayVbankDto.fromEntity(bootpayVbankRepo.save(dto.toEntity()));
    }

    @Override
    public void postReceipt(Long oIdx, String receiptId) {
        Order order = orderRepo.findByIdxAndIsActiveIsTrue(oIdx)
                .orElseThrow(() -> new OrderException("invalid order postReceipt."));
        postReceipt(order, receiptId);
    }

    private void postReceipt(Order order, String receiptId) {
        order.setReceiptId(receiptId);
        orderRepo.save(order);
    }

    @Override
    public void approve(Long oIdx) {
        Order order = orderRepo.findByIdxAndIsActiveIsTrue(oIdx)
                .orElseThrow(() -> new OrderException("invalid order approve."));
        approveSubService.sendFcm(order);
        approveSubService.sendKakaoAT(order);
        commonSubService.log(oIdx, OrderType.ORDER_SUCCESS, OrderType.DELIVERY_READY);
    }

    @Override
    public void disapprove(Long oIdx, String reason) {
        Order order = orderRepo.findByIdxAndIsActiveIsTrue(oIdx)
                .orElseThrow(() -> new OrderException("invalid order disapprove."));

        disapproveSubService.verifyIsValidDisapproveOrder(order.getOrderType());

        commonSubService.rollbackUsingPoint(order);
        commonSubService.rollbackUsingCoupons(order);

        disapproveSubService.sendFcm(order, reason);
        disapproveSubService.sendKakaoAT(order, reason);

        commonSubService.log(oIdx, OrderType.ORDER_REFUSE, OrderType.ORDER_CANCEL);

        // PG 환불
        refundSubService.refundPG(order.getReceiptId(), "시스템", reason, order.paymentCost().doubleValue());
        commonSubService.log(oIdx, OrderType.PAYMENT_REFUND);
    }

    @Override
    public void paymentFail(Long oIdx) {
        Order order = orderRepo.findByIdxAndIsActiveIsTrue(oIdx)
                .orElseThrow(() -> new OrderException("invalid order paymentFail."));

        cancelSubService.verifyIsValidFailOrder(order.getOrderType());

        commonSubService.rollbackUsingPoint(order);
        commonSubService.rollbackUsingCoupons(order);

        commonSubService.log(oIdx, OrderType.PAYMENT_FAIL);
    }

    @Override
    public void cancel(Long oIdx) {
        Order order = orderRepo.findByIdxAndIsActiveIsTrue(oIdx)
                .orElseThrow(() -> new OrderException("invalid order cancel."));

        cancelSubService.verifyIsValidCancelOrder(order.getOrderType());

        commonSubService.rollbackUsingPoint(order);
        commonSubService.rollbackUsingCoupons(order);

        cancelSubService.sendFcm(order);
        cancelSubService.sendKakaoAT(order);

        refundSubService.refundPG(order.getReceiptId(), "시스템", "구매자 취소요청", order.paymentCost().doubleValue());
        commonSubService.log(oIdx, OrderType.ORDER_CANCEL, OrderType.PAYMENT_REFUND);
    }

    @Override
    public void deliveryPickup(Long oIdx) {
        commonSubService.log(oIdx, OrderType.DELIVERY_PICKUP);
    }

    @Override
    public void deliveryDelay(Long oIdx, int min, String reason) {
        Order order = orderRepo.findByIdxAndIsActiveIsTrue(oIdx)
                .orElseThrow(() -> new OrderException("invalid order deliveryDelay."));

        delaySubService.sendFcm(order, min, reason);
        delaySubService.sendKakaoAT(order, min, reason);

        commonSubService.log(oIdx, OrderType.DELIVERY_DELAY);
    }

    @Override
    public void deliverySuccess(Long oIdx) {
        Order order = orderRepo.findByIdxAndIsActiveIsTrue(oIdx)
                .orElseThrow(() -> new OrderException("invalid order deliverySuccess."));

        // 포인트 적립
        commonSubService.commitSavedPoint(order);

        commonSubService.log(oIdx, OrderType.DELIVERY_SUCCESS);
    }

    @Override
    public void refund(Long oIdx) {
        Order order = orderRepo.findByIdxAndIsActiveIsTrue(oIdx)
                .orElseThrow(() -> new OrderException("invalid order refund."));

        refundSubService.verifyIsValidRefundOrder(order.getOrderType());

        // PG 환불
        refundSubService.refundPG(order.getReceiptId(), "시스템", "구매자 환불요청", order.paymentCost().doubleValue());

        // 적립 포인트 회수
        commonSubService.rollbackSavedPoint(order);

        // 사용 포인트 재발급
        commonSubService.rollbackUsingPoint(order);
        commonSubService.rollbackUsingCoupons(order);

        refundSubService.sendFcm(order);
        refundSubService.sendKakaoAT(order);

        commonSubService.log(oIdx, OrderType.PAYMENT_REFUND);
    }

    public void callback(Long oIdx) {
        Order order = orderRepo.findByIdxAndIsActiveIsTrue(oIdx)
                .orElseThrow(() -> new OrderException("invalid order callback."));
        callback(order);
    }

    @Override
    public void callback(CallbackResponse response) {
        Order order = orderRepo.findByReceiptIdAndIsActiveIsTrue(response.getReceipt_id())
                .orElseThrow(() -> new OrderException("invalid order callback."));
        callback(order);
    }

    private void callback(Order order) {
        if(order.getPaymentInfo().getPaymentType() == PaymentType.COMMON_V_BANK) {
            readySubService.addCntOrder(order);
            readySubService.sendFcm(order);
            readySubService.sendKakaoAT(order);

            orderDepositSubService.sendFcm(order);
            orderDepositSubService.sendKakaoAT(order);

            commonSubService.log(order.getIdx(), OrderType.PAYMENT_SUCCESS, OrderType.ORDER_READY);
        }
    }

    private Order _save(OrderRequestDto dto) {
        Order entity = dto.toEntity();
        DeliveryDetailSite ddSite = deliveryDetailSiteRepo.findByIdxAndIsActiveIsTrue(dto.getIdxDeliveryDetailSite());
        entity.setBoxNumber(orderRepo.boxNumber(ddSite.getDeliverySite().getIdx(), dto.getIdxOrderTime(), dto.getOrderDate()));
        entity.getPaymentInfo().setSavedPoint(0);
        return orderRepo.saveAndFlush(entity);
    }
}
