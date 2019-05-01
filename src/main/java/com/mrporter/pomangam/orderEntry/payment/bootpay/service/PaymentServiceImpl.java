package com.mrporter.pomangam.orderEntry.payment.bootpay.service;

import com.google.gson.Gson;
import com.mrporter.pomangam.common.map.service.CommonMapServiceImpl;
import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.repository.UserJpaRepository;
import com.mrporter.pomangam.common.security.user.service.UserServiceImpl;
import com.mrporter.pomangam.common.util.choseong.InitialConsonant;
import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.deliveryEntry.deliverySite.repository.DeliverySiteRepositoryImpl;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySiteDto;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository.DetailForDeliverySiteJpaRepository;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository.DetailForDeliverySiteRepositoryImpl;
import com.mrporter.pomangam.orderEntry.cart.domain.Cart;
import com.mrporter.pomangam.orderEntry.cart.repository.CartJpaRepository;
import com.mrporter.pomangam.orderEntry.cart.service.CartServiceImpl;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItem;
import com.mrporter.pomangam.orderEntry.cartItem.repository.CartItemJpaRepository;
import com.mrporter.pomangam.orderEntry.order.domain.Order;
import com.mrporter.pomangam.orderEntry.order.domain.OrderInfoDto;
import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.orderEntry.order.domain.StatePayment;
import com.mrporter.pomangam.orderEntry.order.repository.OrderJpaRepository;
import com.mrporter.pomangam.orderEntry.order.repository.OrderRepositoryImpl;
import com.mrporter.pomangam.orderEntry.order.service.OrderServiceImpl;
import com.mrporter.pomangam.orderEntry.orderItem.domain.OrderInfoItemDto;
import com.mrporter.pomangam.orderEntry.orderItem.domain.OrderItem;
import com.mrporter.pomangam.orderEntry.orderItem.repository.OrderItemJpaRepository;
import com.mrporter.pomangam.orderEntry.orderLog.domain.OrderLog;
import com.mrporter.pomangam.orderEntry.orderLog.repository.OrderLogJpaRepository;
import com.mrporter.pomangam.orderEntry.orderLog.service.OrderLogServiceImpl;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.*;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.request.Cancel;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.request.SubscribeBilling;
import com.mrporter.pomangam.productEntry.product.domain.ProductWithCostDto;
import com.mrporter.pomangam.productEntry.product.repository.ProductRepositoryImpl;
import com.mrporter.pomangam.promotionEntry.coupon.domain.CouponDto;
import com.mrporter.pomangam.promotionEntry.coupon.repository.CouponRepositoryImpl;
import com.mrporter.pomangam.promotionEntry.pointLog.domain.StatePointLog;
import com.mrporter.pomangam.promotionEntry.pointLog.service.PointLogServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private static final String APPLICATION_ID = "5cc70f38396fa67747bd0686";
    private static final String PRIVATE_KEY = "/LokN9TIOXtFpmIo9iUEl76GllpuKljfbR0ndCviINU=";
    static BootpayApi api = new BootpayApi(APPLICATION_ID, PRIVATE_KEY);

    DetailForDeliverySiteJpaRepository detailForDeliverySiteJpaRepository;
    CartJpaRepository cartJpaRepository;
    CartServiceImpl cartService;
    CartItemJpaRepository cartItemJpaRepository;
    OrderRepositoryImpl orderRepository;
    OrderJpaRepository orderJpaRepository;
    OrderItemJpaRepository orderItemJpaRepository;
    OrderServiceImpl orderService;
    UserServiceImpl userService;
    UserJpaRepository userJpaRepository;
    CouponRepositoryImpl couponRepository;
    OrderLogServiceImpl orderLogService;
    OrderLogJpaRepository orderLogJpaRepository;
    ProductRepositoryImpl productRepository;
    DeliverySiteRepositoryImpl deliverySiteRepository;
    PointLogServiceImpl pointLogService;
    CommonMapServiceImpl commonMapService;
    DetailForDeliverySiteRepositoryImpl detailForDeliverySiteRepository;

    public static String getPrivate_key() {
        return PRIVATE_KEY;
    }

    @Override
    public PaymentOutputDto prepare(PaymentInputDto dto) {
        String orderId = "";
        Integer orderIdx = null;
        try {
            PaymentOutputDto result = new PaymentOutputDto();

            Integer cartIdx = dto.getCartIdx();
            int usingPoint = dto.getUsingPoint() == null ? 0 : dto.getUsingPoint().intValue();
            int discountPct = 0;
            int discountPrc = 0;
            Integer usingCouponIdx = null;
            String usingCouponCode = dto.getUsingCouponCode();
            orderId = "ORDER_" + cartIdx + "_" + System.currentTimeMillis();

            Cart cart = cartJpaRepository.getOne(cartIdx);
            if(cart == null) {
                log.error("[INVALID CART IDX] - orderId : '{}'", orderId);
                return null;
            }
            Integer customerIdx = cart.getCustomerIdx();
            Integer guestIdx = cart.getGuestIdx();
            Integer deliverySiteIdx = detailForDeliverySiteJpaRepository.getOne(cart.getDetailSiteIdx()).getDeliverySiteIdx();
            String arrivalDate = cart.getArrivalDate().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String arrivalTime = cart.getArrivalDate().toLocalDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss"));

            /* 핸드폰 */
            if(customerIdx != null) {
                User user = userJpaRepository.getOne(customerIdx);
                result.setPhone(user.getPhoneNumber());
                result.setCustomerId(user.getId());
                result.setCustomerName(user.getName());
                result.setDeliverySiteName(deliverySiteRepository.getByDeliverySiteIdx(deliverySiteIdx).getName());
            } else {
                result.setPhone(dto.getPhone());
            }

            /* 포인트 */
            if(customerIdx == null) {
                usingPoint = 0;
            } else {
                if(usingPoint > userService.getPointByIdx(customerIdx)) {
                    log.error("[INVALID POINT VALUE] - orderId : '{}'", orderId);
                    return null;
                }
            }

            /* 쿠폰 */
            CouponDto coupon = couponRepository.getValidCouponByCode(usingCouponCode);
            if(coupon != null) {
                usingCouponIdx = coupon.getIdx();
                discountPct = coupon.getDiscount_pct().intValue();
                discountPrc = coupon.getDiscount_prc().intValue();
            }

            int final_amount = cartService.getTotalAmount(cartIdx);
            final_amount = final_amount * (1 - discountPct / 100) - discountPrc;
            final_amount -= usingPoint;

            /* OrderLog */
            Order order = orderJpaRepository.save(Order.builder()
                    .box_no(orderRepository.getBoxNo(deliverySiteIdx, arrivalDate, arrivalTime))
                    .customer_idx(customerIdx)
                    .guest_idx(guestIdx)
                    .employee_idx(null)
                    .delivery_site_idx(deliverySiteIdx)
                    .detail_site_idx(cart.getDetailSiteIdx())
                    .type_payment(dto.getTypePayment())
                    .state_order(StateOrder.ORDER_READY.getCode())
                    .register_date(CustomTime.curTimestampSql())
                    .arrival_date_only(Date.valueOf(arrivalDate))
                    .arrival_time_only(Time.valueOf(arrivalTime))
                    .using_point(dto.getUsingPoint())
                    .using_coupon_idx(usingCouponIdx)
                    .final_amount(final_amount)
                    .saved_point(null)
                    .orderId(orderId)
                    .phone(result.getPhone())
                    .build()
            );
            orderIdx = order.getIdx();

            /* logging */
            orderLogJpaRepository.save(new OrderLog(order));

            /* OrderItem */
            List<CartItem> cartItems = cartItemJpaRepository.findByCartIdx(cartIdx);
            List<OrderInfoItemDto> orderInfoItems = new ArrayList<>();
            for(CartItem ci : cartItems) {
                ProductWithCostDto product = productRepository.findByProductIdx(ci.getProductIdx());
                orderInfoItems.add(OrderInfoItemDto.builder()
                        .product_idx(product.getIdx())
                        .product_name(product.getName())
                        .quantity(ci.getQuantity())
                        .requirement(ci.getRequirement())
                        .parent_item_idx(ci.getParentItemIdx())
                        .unit_amount(product.getFinal_cost().intValue())
                        .build());

                if(ci.getParentItemIdx() == null) {
                    OrderItem oi = orderItemJpaRepository.save(OrderItem.builder()
                            .order_idx(order.getIdx())
                            .store_idx(ci.getStoreIdx())
                            .product_idx(ci.getProductIdx())
                            .quantity(ci.getQuantity())
                            .requirement(ci.getRequirement())
                            .parent_item_idx(ci.getParentItemIdx())
                            .unit_amount(product.getFinal_cost().intValue())
                            .build());
                    for(CartItem ci2 : cartItems) {
                        if(ci2.getParentItemIdx() != null && ci2.getParentItemIdx().intValue() == ci.getIdx().intValue()) {
                            product = productRepository.findByProductIdx(ci2.getProductIdx());
                            orderItemJpaRepository.save(OrderItem.builder()
                                    .order_idx(order.getIdx())
                                    .store_idx(ci2.getStoreIdx())
                                    .product_idx(ci2.getProductIdx())
                                    .quantity(ci2.getQuantity())
                                    .requirement(ci2.getRequirement())
                                    .parent_item_idx(oi.getIdx())
                                    .unit_amount(product.getFinal_cost().intValue())
                                    .build());
                        }
                    }
                }

            }

            /* Payment Output */
            result.setOrderId(orderId);
            result.setFinal_amount(order.getFinal_amount());
            result.setOrderItems(orderInfoItems);

            return result;

        } catch (Exception e) {
            if(orderIdx != null) {
                orderService.setState(orderIdx, StateOrder.ORDER_FAIL);
            }
            e.printStackTrace();
            log.error("[ERROR Exception] - orderId : '{}' Cause : {}", orderId,  e.toString());
            return null;
        }
    }

    @Override
    public OrderInfoDto complete(CompleteInputDto dto) {
        String receiptId = dto.getReceiptId();
        String orderId = dto.getOrderId();
        Integer orderIdx = null;
        try {

            /*  부트페이 API 결제정보 조회 */
            goGetToken();
            VerifyDto verifyDto = goVerify(receiptId);
            if(verifyDto.getStatus() != 200) {
                failLog(orderId, "WRONG TOKEN");
                return null;
            }

            VerifyDataDto data = verifyDto.getData();
            Order order = orderJpaRepository.getByOrderId(orderId);
            OrderLog orderLog = orderLogJpaRepository.getByOrderId(orderId);
            orderIdx = order.getIdx();
            orderService.setReceiptId(orderIdx, receiptId);

            /* 결과 상태 검증 */
            if(data.getStatus().intValue() != 1) {
                failLog(orderId, "STATUS IS INVALID");
                return null;
            }

            /* amount 가 가맹점에서 계산한 {실제 주문금액}과 일치하는지 최종 검증 */
            if(data.getPrice().intValue() != order.getFinal_amount().intValue()) {
                failLog(orderId, "FORGERY AMOUNT");
                return null;
            }

            /* 포인트 */
            Integer customerIdx = order.getCustomer_idx();
            Integer guestIdx = order.getGuest_idx();
            int usingPoint = order.getUsing_point() == null ? 0 : order.getUsing_point();
            if(customerIdx != null) {
                if(usingPoint > userService.getPointByIdx(customerIdx)) {
                    failLog(orderId, "INVALID POINT VALUE");
                    return null;
                } else {
                    userService.minusPointByIdx(customerIdx, usingPoint);
                    pointLogService.logUsed(customerIdx, orderIdx, usingPoint, StatePointLog.USED_BY_PAYMENT);
                }
            }

            /* 쿠폰 */
            Integer usingCouponIdx = order.getUsing_coupon_idx();
            CouponDto coupon = couponRepository.getValidCouponByIdx(usingCouponIdx);
            if(coupon != null) {
                couponRepository.useCoupon(coupon.getIdx(), customerIdx, guestIdx, orderIdx); // 로깅 포함
            }

            /* 포인트 적립(회원) */
            if(customerIdx != null) {
                int savedPoint;
                int pointSavingPct = Integer.parseInt(commonMapService.getValue("point-saving-pct-1").get(0).getValue());
                int pointSavingPrc = Integer.parseInt(commonMapService.getValue("point-saving-prc-1").get(0).getValue());
                savedPoint = data.getPrice().intValue() * (1 - pointSavingPct / 100) - pointSavingPrc;
                userService.plusPointByIdx(customerIdx, savedPoint);
                pointLogService.logSaved(customerIdx, orderIdx, usingPoint, StatePointLog.ISSUE_BY_PAYMENT);
                order.setSaved_point(savedPoint);
                orderLog.setSaved_point(savedPoint);
            }

            /* Remove Cart & CartItem (cascade) */
            Integer cartIdx = Integer.parseInt(orderId.split("_")[1]);
            cartJpaRepository.deleteById(cartIdx);

            /* 결제 상태 변경 */
            order.setState_order(StateOrder.ORDER_SUCCESS.getCode());
            orderLog.setState_order(StateOrder.ORDER_SUCCESS.getCode());
            orderLog.setReceipt_id(receiptId);

            orderLogJpaRepository.save(orderLog);
            orderJpaRepository.save(order);

            DetailForDeliverySiteDto detailForDeliverySiteDto = detailForDeliverySiteRepository.getByIdx(order.getDetail_site_idx());
            OrderInfoDto info = OrderInfoDto.builder()
                    .order_idx(order.getIdx())
                    .box_no(InitialConsonant.getInitial(detailForDeliverySiteDto.getAbbreviation())+"-"+order.getBox_no())
                    .delivery_site_name(deliverySiteRepository.getByDeliverySiteIdx(order.getDelivery_site_idx()).getName())
                    .detail_site_name(detailForDeliverySiteDto.getName())
                    .register_date(order.getRegister_date())
                    .type_payment(StatePayment.toString(order.getType_payment()))
                    .state_order(StateOrder.toString(order.getState_order()))
                    .arrival_date_only(order.getArrival_date_only())
                    .arrival_time_only(order.getArrival_time_only())
                    .using_point(order.getUsing_point() == null ? 0 : order.getUsing_point())
                    .using_coupon_name(coupon == null ? null : coupon.getName())
                    .using_coupon_amount(coupon == null ? 0 : coupon.getDiscount_prc())
                    .final_amount(order.getFinal_amount())
                    .saved_point(order.getSaved_point() == null ? 0 : order.getSaved_point())
                    .receipt_id(order.getReceiptId())
                    .order_id(order.getOrderId())
                    //.orderItems(orderItemRepository.findOrderInfoItem(order.getIdx()))
                    .build();
            return info;

        } catch (Exception e) {
            e.printStackTrace();
            if(orderIdx != null) {
                orderService.setReceiptId(orderIdx, receiptId);
                orderService.setState(orderIdx, StateOrder.ORDER_FAIL);
            }
            log.error("[ERROR Exception] - orderId : '{}' Cause : {}", orderId,  e.toString());
            return null;
        }
    }

    public void failLog(String orderId, String msg) {
        orderService.setState(orderId, StateOrder.ORDER_FAIL);
        orderLogService.setState(orderId, StateOrder.ORDER_FAIL);
        log.error("[" + msg + "] - orderId : '{}'", orderId);
    }

    @Override
    public CancelDto cancel(Cancel cancel) {
        goGetToken();
        return goCancel(cancel);
    }

    @Override
    public void fail(FailInputDto dto) {
        String orderId = dto.getOrderId();
        log.error("[" + dto.getMessage() + "] - orderId : '{}'", orderId);
        switch (dto.getStatus()) {
            case -100 :
                // 사용자에 의한 취소
                orderService.deleteByOrderId(orderId);
                break;
            default:
                orderService.setState(orderId, StateOrder.ORDER_FAIL);
                orderLogService.setState(orderId, StateOrder.ORDER_FAIL);
                break;
        }
    }

    public static void goGetToken() {
        try {
            if(!api.hasToken()) {
                api.getAccessToken();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VerifyDto goVerify(String receiptId) {
        try {
            HttpResponse res = api.verify(receiptId);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            VerifyDto dto = new Gson().fromJson(str, VerifyDto.class);
            if(dto.getStatus() == 401) {
                // TOKEN INVALID OR EXPIRED
                api.getAccessToken();
                res = api.verify(receiptId);
                str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
                dto = new Gson().fromJson(str, VerifyDto.class);
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CancelDto goCancel(Cancel cancel) {
        try {
            HttpResponse res = api.cancel(cancel);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");

            CancelDto dto = new Gson().fromJson(str, CancelDto.class);
            if(dto.getStatus() == 401) {
                // TOKEN INVALID OR EXPIRED
                api.getAccessToken();
                res = api.cancel(cancel);
                str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
                dto = new Gson().fromJson(str, CancelDto.class);
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void goSubscribeBilling() {
        SubscribeBilling subscribeBilling = new SubscribeBilling();
        subscribeBilling.billing_key = "5b025b33e13f33310ce560fb";
        subscribeBilling.item_name = "정기결제 테스트 아이템";
        subscribeBilling.price = 3000;
        subscribeBilling.order_id = "" + (System.currentTimeMillis() / 1000);

        try {
            HttpResponse res = api.subscribe_billing(subscribeBilling);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
            System.out.println(new Gson().toJson(subscribeBilling));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

