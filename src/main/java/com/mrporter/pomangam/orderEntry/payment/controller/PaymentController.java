package com.mrporter.pomangam.orderEntry.payment.controller;

import com.mrporter.pomangam.common.map.service.CommonMapServiceImpl;
import com.mrporter.pomangam.common.security.user.service.UserServiceImpl;
import com.mrporter.pomangam.common.util.apiClient.ImpApi;
import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository.DetailForDeliverySiteJpaRepository;
import com.mrporter.pomangam.orderEntry.cart.domain.Cart;
import com.mrporter.pomangam.orderEntry.cart.repository.CartJpaRepository;
import com.mrporter.pomangam.orderEntry.cart.service.CartServiceImpl;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItem;
import com.mrporter.pomangam.orderEntry.cartItem.repository.CartItemJpaRepository;
import com.mrporter.pomangam.orderEntry.order.domain.Order;
import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.orderEntry.order.repository.OrderJpaRepository;
import com.mrporter.pomangam.orderEntry.order.repository.OrderRepositoryImpl;
import com.mrporter.pomangam.orderEntry.order.service.OrderServiceImpl;
import com.mrporter.pomangam.orderEntry.orderItem.domain.OrderItem;
import com.mrporter.pomangam.orderEntry.orderItem.repository.OrderItemJpaRepository;
import com.mrporter.pomangam.orderEntry.orderLog.repository.OrderLogJpaRepository;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentAnnotation;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentInputDto;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentResultDto;
import com.mrporter.pomangam.productEntry.product.repository.ProductRepositoryImpl;
import com.mrporter.pomangam.promotionEntry.coupon.domain.CouponDto;
import com.mrporter.pomangam.promotionEntry.coupon.repository.CouponRepositoryImpl;
import com.mrporter.pomangam.promotionEntry.pointLog.domain.StatePointLog;
import com.mrporter.pomangam.promotionEntry.pointLog.service.PointLogServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/payments")
@RestController
@AllArgsConstructor
public class PaymentController {

    DetailForDeliverySiteJpaRepository detailForDeliverySiteJpaRepository;
    CartJpaRepository cartJpaRepository;
    CartServiceImpl cartService;
    CartItemJpaRepository cartItemJpaRepository;
    OrderRepositoryImpl orderRepository;
    OrderJpaRepository orderJpaRepository;
    OrderItemJpaRepository orderItemJpaRepository;
    OrderServiceImpl orderService;
    UserServiceImpl userService;
    CouponRepositoryImpl couponRepository;
    OrderLogJpaRepository orderLogJpaRepository;
    CommonMapServiceImpl commonMapService;
    ProductRepositoryImpl productRepository;
    PointLogServiceImpl pointLogService;

    @PostMapping("/prepare")
    public ResponseEntity<?> prepare(@RequestBody PaymentInputDto dto) {
        Integer orderIdx = null;
        try {
            Integer cartIdx = dto.getCartIdx();
            String merchant_uid = "merchant_" + cartIdx + "_" + System.currentTimeMillis();
            int usingPoint = dto.getUsingPoint() == null ? 0 : dto.getUsingPoint().intValue();
            int discountPct = 0;
            int discountPrc = 0;
            Integer usingCouponIdx = null;
            String usingCouponCode = dto.getUsingCouponCode();

            Cart cart = cartJpaRepository.getOne(cartIdx);
            Integer customerIdx = cart.getCustomerIdx();
            Integer guestIdx = cart.getGuestIdx();
            Integer deliverySiteIdx = detailForDeliverySiteJpaRepository.getOne(cart.getDetailSiteIdx()).getDeliverySiteIdx();
            String arrivalDate = cart.getArrivalDate().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String arrivalTime = cart.getArrivalDate().toLocalDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss"));

            /* 포인트 */
            if(customerIdx == null) {
                usingPoint = 0;
            } else {
                if(usingPoint > userService.getPointByIdx(customerIdx)) {
                    return new ResponseEntity<>("INVALID POINT VALUE", HttpStatus.BAD_REQUEST);
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
            Order order = orderJpaRepository.save(
                    new Order(
                            orderRepository.getBoxNo(deliverySiteIdx, arrivalDate, arrivalTime),
                            customerIdx,
                            guestIdx,
                            null,
                            deliverySiteIdx,
                            cart.getDetailSiteIdx(),
                            dto.getTypePayment(),
                            StateOrder.ORDER_READY.getCode(),
                            CustomTime.curTimestampSql(),
                            Date.valueOf(arrivalDate),
                            Time.valueOf(arrivalTime),
                            dto.getUsingPoint(),
                            usingCouponIdx,
                            final_amount,
                            merchant_uid,
                            null,
                            null
                    ));
            orderIdx = order.getIdx();

            /* logging
            orderLogJpaRepository.save(
                    new OrderLog(
                    order.getBox_no(),
                    order.getCustomer_idx(),
                    order.getGuest_idx(),
                    order.getEmployee_idx(),
                    order.getDelivery_site_idx(),
                    order.getDetail_site_idx(),
                    order.getType_payment(),
                    order.getState_order(),
                    order.getRegister_date(),
                    order.getArrival_date_only(),
                    order.getArrival_time_only(),
                    order.getUsing_point(),
                    order.getUsing_coupon_idx(),
                    order.getFinal_amount(),
                    order.getMerchantUid()
            ));
            */

            /* OrderItem */
            List<OrderItem> orderItems = new ArrayList<>();
            List<CartItem> cartItems = cartItemJpaRepository.findByCartIdx(cartIdx);
            for(CartItem ci : cartItems) {
                OrderItem oi = new OrderItem(
                        order.getIdx(),
                        ci.getStoreIdx(),
                        ci.getProductIdx(),
                        ci.getQuantity(),
                        ci.getRequirement(),
                        ci.getParentItemIdx(),
                        productRepository.findByProductIdx(ci.getProductIdx()).getFinal_cost().intValue()
                );
                orderItems.add(oi);
            }
            orderItemJpaRepository.saveAll(orderItems);

            /* prepare api 등록 */
            if( ImpApi.prepare(merchant_uid, final_amount) ) {
                return new ResponseEntity<>(order, HttpStatus.OK);
            } else {
                orderService.setState(orderIdx, StateOrder.ORDER_FAIL);
                return new ResponseEntity<>("ERROR ImpApi", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(orderIdx != null) {
                orderService.setState(orderIdx, StateOrder.ORDER_FAIL);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/fail")
    public ResponseEntity<?> fail(@PathVariable(name = "id") Integer order_idx) {
        orderService.setState(order_idx, StateOrder.ORDER_FAIL);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/complete/mobile")
    public ResponseEntity<?> completeMobileGet(@RequestParam(name = "imp_uid") String imp_uid,
                                               @RequestParam(name = "merchant_uid") String merchant_uid) {
        PaymentResultDto pdto = new PaymentResultDto(imp_uid, merchant_uid);
        return completePost(pdto);
    }

    @PostMapping("/complete/mobile")
    public ResponseEntity<?> completeMobilePost(@RequestParam(name = "imp_uid") String imp_uid,
                                      @RequestParam(name = "merchant_uid") String merchant_uid) {
        PaymentResultDto pdto = new PaymentResultDto(imp_uid, merchant_uid);
        return completePost(pdto);
    }

    @GetMapping("/complete")
    public ResponseEntity<?> completeGet(@RequestBody PaymentResultDto pdto) {
        return completePost(pdto);
    }

    @PostMapping("/complete")
    public ResponseEntity<?> completePost(@RequestBody PaymentResultDto pdto) {
        Integer orderIdx = null;
        try {
            String imp_uid = pdto.getImp_uid();
            String merchant_uid = pdto.getMerchant_uid();

            /*  아임포트 API 결제정보를 조회 */
            PaymentAnnotation paymentInfo = ImpApi.getInfo(imp_uid);
            Order order = orderJpaRepository.getByMerchantUid(merchant_uid);
            orderIdx = order.getIdx();
            //System.out.println("paymentInfo : " + paymentInfo);

            if(!paymentInfo.getStatus().equals("paid")) {
                // fail
                orderService.setState(order.getIdx(), StateOrder.ORDER_FAIL);
                return new ResponseEntity<>("STATUS IS NOT PAID", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            /* amount 가 가맹점에서 계산한 {실제 주문금액}과 일치하는지 최종 검증 */
            int imp_amount = paymentInfo.getAmount().intValue();
            int cli_amount = order.getFinal_amount();
            if(imp_amount != cli_amount) {
                // fail
                orderService.setState(orderIdx, StateOrder.ORDER_FAIL);
                return new ResponseEntity<>("FORGERY AMOUNT", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            /* Remove Cart & CartItem (cascade) */
            Integer cartIdx = Integer.parseInt(merchant_uid.split("_")[1]);
            cartJpaRepository.deleteById(cartIdx);

            /* 결제 상태 변경 */
            //orderService.setState(orderIdx, StateOrder.ORDER_SUCCESS);
            //orderService.setImpUid(orderIdx, imp_uid);
            order.setState_order(StateOrder.ORDER_SUCCESS.getCode());
            order.setImp_uid(imp_uid);


            /* 사용 포인트 제거, 쿠폰 제거 */

            /* 포인트 */
            Integer customerIdx = order.getCustomer_idx();
            Integer guestIdx = order.getGuest_idx();
            int usingPoint = order.getUsing_point() == null ? 0 : order.getUsing_point();
            if(customerIdx != null) {
                if(usingPoint > userService.getPointByIdx(customerIdx)) {
                    orderService.setState(orderIdx, StateOrder.ORDER_FAIL);
                    return new ResponseEntity<>("INVALID POINT VALUE", HttpStatus.BAD_REQUEST);
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
                int pointSavingPct = Integer.parseInt(commonMapService.getValue("point-saving-pct-1") + "");
                int pointSavingPrc = Integer.parseInt(commonMapService.getValue("point-saving-prc-1") + "");
                savedPoint = imp_amount * (1 - pointSavingPct / 100) - pointSavingPrc;
                userService.plusPointByIdx(customerIdx, savedPoint);
                pointLogService.logSaved(customerIdx, orderIdx, usingPoint, StatePointLog.ISSUE_BY_PAYMENT);
                order.setSaved_point(savedPoint);
            }

            return new ResponseEntity(orderJpaRepository.save(order), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            if(orderIdx != null) {
                orderService.setState(orderIdx, StateOrder.ORDER_FAIL);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
