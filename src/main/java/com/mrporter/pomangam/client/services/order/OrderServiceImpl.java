package com.mrporter.pomangam.client.services.order;

import com.google.common.annotations.VisibleForTesting;
import com.mrporter.pomangam.client.domains.coupon.Coupon;
import com.mrporter.pomangam.client.domains.coupon.CouponMapper;
import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderRequestDto;
import com.mrporter.pomangam.client.domains.order.OrderResponseDto;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.order.log.OrderLog;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.point.log.PointType;
import com.mrporter.pomangam.client.repositories.coupon.CouponJpaRepository;
import com.mrporter.pomangam.client.repositories.coupon.CouponMapperJpaRepository;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import com.mrporter.pomangam.client.repositories.order.OrderLogJpaRepository;
import com.mrporter.pomangam.client.services.order.exception.OrderException;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    EntityManager em;
    OrderJpaRepository orderRepo;
    OrderLogJpaRepository orderLogRepo;
    UserServiceImpl userService;
    CouponJpaRepository couponRepo;
    CouponMapperJpaRepository couponMapperRepo;

    @Override
    public List<OrderResponseDto> findByPhoneNumber(String phoneNumber, Pageable pageable) {
        return OrderResponseDto.fromEntities(orderRepo.findByOrderer_User_PhoneNumberAndIsActiveIsTrue(phoneNumber, pageable).getContent());
    }

    @Override
    public OrderResponseDto save(OrderRequestDto dto) {
        Long idxOrder = _save(dto).getIdx();
        log(idxOrder, OrderType.PAYMENT_READY);
        em.clear(); // 1차 캐시 제거 -> 새로운 entity 받아옴 (이전 saved entity 는 빈 껍데기..)

        Order order = orderRepo.findByIdxAndIsActiveIsTrue(idxOrder)
                .orElseThrow(() -> new OrderException("invalid order save"));
        verifyUsingPoint(order); // 사용 포인트 검증
        verifyUsingCoupons(order, dto.getIdxesUsingCoupons()); // 사용 쿠폰 검증
        verifyUsingPromotions(order, dto.getIdxesUsingPromotions()); // 프로모션 검증
        verifySavedPoint(order); // 적립 포인트 검증

        return OrderResponseDto.fromEntity(order);
    }

    @Override
    public boolean verify(Long oIdx) {
        Order order = orderRepo.findByIdxAndOrderTypeAndIsActiveIsTrue(oIdx, OrderType.PAYMENT_READY)
                .orElseThrow(() -> new OrderException("invalid order verify."));

        boolean isVerified = _verifyPG(oIdx, order.paymentCost());
        if(isVerified) {
            commitSavedPoint(order);
            log(oIdx, OrderType.PAYMENT_SUCCESS, OrderType.ORDER_READY);
        } else {
            rollbackUsingPoint(order);
            rollbackUsingCoupons(order);
            log(oIdx, OrderType.PAYMENT_FAIL);
        }
        return isVerified;
    }

    @Override
    public void cancel(Long oIdx) {
        Order order = orderRepo.findByIdxAndOrderTypeAndIsActiveIsTrue(oIdx, OrderType.PAYMENT_READY)
                .orElseThrow(() -> new OrderException("invalid order verify."));
        rollbackUsingPoint(order);
        rollbackUsingCoupons(order);
        log(oIdx, OrderType.PAYMENT_FAIL);
    }

    @Override
    public void log(Long idxOrder, OrderType ...orderTypes) {
        if(orderTypes == null || orderTypes.length == 0) return;

        // log 상태 변경
        List<OrderLog> orderLogs = new ArrayList<>();
        for(OrderType orderType : orderTypes) {
            orderLogs.add(OrderLog.builder()
                    .idxOrder(idxOrder)
                    .orderType(orderType)
                    .build());
        }
        orderLogRepo.saveAll(orderLogs);

        // order 상태 변경
        Order order = orderRepo.findById(idxOrder)
                .orElseThrow(() -> new RuntimeException("invalid order"));
        order.setOrderType(orderTypes[orderTypes.length-1]); // 최종 상태
        orderRepo.save(order);
    }

    @VisibleForTesting
    public boolean _verifyPG(Long oIdx, int paymentCost) {
        // dummy code
        // Todo..
        System.out.println("PG] oIdx: " + oIdx + " - paymentCost: " + paymentCost + "원");
        return true;
    }

    @VisibleForTesting
    public Order _save(OrderRequestDto dto) {
        Order entity = dto.toEntity();
        entity.setBoxNumber(orderRepo.boxNumber(dto.getIdxDeliveryDetailSite(), dto.getIdxOrderTime(), dto.getOrderDate()));
        entity.getPaymentInfo().setSavedPoint(0);
        return orderRepo.saveAndFlush(entity);
    }

    @VisibleForTesting
    public void verifyUsingPoint(Order order) {
        User user = order.getOrderer().getUser();
        if(user != null) {
            int userPoint = user.getPoint();
            int usingPoint = order.getPaymentInfo().getUsingPoint();
            if(usingPoint == 0) return;
            if(userPoint < usingPoint || usingPoint < 0) {
                log(order.getIdx(), OrderType.PAYMENT_READY_FAIL_POINT);
                throw new OrderException("invalid using point.");
            }
            userService.minusPointByIdx(user.getIdx(), usingPoint, PointType.USED_BY_BUY);
        }
    }

    @VisibleForTesting
    public void verifyUsingCoupons(Order order, Set<Long> idxesUsingCoupons) {
        User user = order.getOrderer().getUser();
        if(user != null && idxesUsingCoupons != null && !idxesUsingCoupons.isEmpty()) {
            List<CouponMapper> couponMappers = new ArrayList<>();
            List<Coupon> userCoupons = couponRepo.findByUser_IdxAndIsActiveIsTrueAndIsUsedIsFalse(user.getIdx());
            for(Long idxUsingCoupon : idxesUsingCoupons) {
                Coupon coupon = findCoupon(userCoupons, idxUsingCoupon);
                if(coupon == null || !coupon.isValid()) {
                    log(order.getIdx(), OrderType.PAYMENT_READY_FAIL_COUPON);
                    throw new OrderException("invalid using coupon.");
                }
                coupon.setIsUsed(true);
                couponMappers.add(CouponMapper.builder()
                        .order(order)
                        .coupon(coupon)
                        .build());
            }
            couponMapperRepo.saveAll(couponMappers);
            order.getPaymentInfo().setUsingCoupons(couponMappers);
        }
    }

    @VisibleForTesting
    public Coupon findCoupon(List<Coupon> userCoupons, Long findIdx) {
        for(Coupon userCoupon : userCoupons) {
            if(findIdx.compareTo(userCoupon.getIdx()) == 0) {
                return userCoupon;
            }
        }
        return null;
    }

    @VisibleForTesting
    public void verifyUsingPromotions(Order order, Set<Long> idxesUsingPromotions) {
        // Todo. 프로모션 개발
    }

    @VisibleForTesting
    public void verifySavedPoint(Order order) {
        User user = order.getOrderer().getUser();
        if(user != null) {
            int percentSavePoint = user.getPointRank().getPercentSavePoint();
            int priceSavePoint = user.getPointRank().getPriceSavePoint();
            int paymentCost = order.paymentCost();
            int savedPoint = (paymentCost * percentSavePoint / 100) + priceSavePoint;
            order.getPaymentInfo().setSavedPoint(savedPoint);
            orderRepo.save(order);
        }
    }

    @VisibleForTesting
    public void commitSavedPoint(Order order) {
        User user = order.getOrderer().getUser();
        int savedPoint = order.getPaymentInfo().getSavedPoint();
        userService.plusPointByIdx(user.getIdx(), savedPoint, PointType.ISSUED_BY_BUY);
    }

    @VisibleForTesting
    public void rollbackUsingPoint(Order order) {
        User user = order.getOrderer().getUser();
        int usingPoint = order.getPaymentInfo().getUsingPoint();
        if(usingPoint > 0) {
            userService.plusPointByIdx(user.getIdx(), usingPoint, PointType.ROLLBACK_BY_PAYMENT_CANCEL);
        }
    }

    @VisibleForTesting
    public void rollbackUsingCoupons(Order order) {
        List<CouponMapper> couponMappers = couponMapperRepo.findByOrder_Idx(order.getIdx());
        for(CouponMapper couponMapper : couponMappers) {
            Coupon coupon = couponMapper.getCoupon();
            coupon.setIsUsed(false);
            couponRepo.save(coupon);
            couponMapperRepo.delete(couponMapper);
            order.getPaymentInfo().usingCouponsClear(); // order 쪽도 변경해줘야 함.
        }
    }
}
