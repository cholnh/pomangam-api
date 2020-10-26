package com.mrporter.pomangam.test.data.order;

import com.mrporter.pomangam.client.domains.payment.PaymentType;
import com.mrporter.pomangam.client.domains.user.coupon.Coupon;
import com.mrporter.pomangam.client.domains.user.coupon.CouponMapper;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.order.item.OrderItem;
import com.mrporter.pomangam.client.domains.order.orderer.Orderer;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.domains.order.payment_info.PaymentInfo;
import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.user.coupon.CouponMapperJpaRepository;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class OrderData {

    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Autowired
    CouponMapperJpaRepository couponMapperJpaRepository;

    @Transactional
    public void of(Long idx, int boxIdx, Long ddIdx, LocalDate orderDate, Long oIdx, PaymentType paymentType, OrderType orderType, Long sIdx, Long pIdx, int quantity, Long ...idxesCoupon) {
        Order order = Order.builder()
                .idx(idx)
                .boxNumber((short) boxIdx)
                .deliveryDetailSite(DeliveryDetailSite.builder().idx(ddIdx).build())
                .orderer(Orderer.builder()
                        .idxFcmToken(1L)
                        .ordererType(OrdererType.USER)
                        .user(User.builder().idx(1L).build())
                        .build())
                .orderDate(orderDate)
                .orderTime(OrderTime.builder().idx(oIdx).build())
                .orderType(orderType)
                .paymentInfo(PaymentInfo.builder()
                        .paymentType(paymentType)
                        .usingPoint(0)
                        .savedPoint(0)
                        .build())
                .build();
        OrderItem orderItem1 = OrderItem.builder()
                .store(Store.builder().idx(sIdx).build())
                .product(Product.builder().idx(pIdx).build())
                .quantity((short) quantity)
                .requirement("box : " + boxIdx + " orderTime : " + oIdx + " detail : " + ddIdx)
                .isReviewWrite(false)
                .build();
        order.addItem(orderItem1);
        orderJpaRepository.save(order);

        // coupon
        for(Long idxCoupon: idxesCoupon) {
            CouponMapper couponMapper = CouponMapper.builder()
                    .order(Order.builder().idx(idx).build())
                    .coupon(Coupon.builder().idx(idxCoupon).build())
                    .build();
            couponMapperJpaRepository.save(couponMapper);
        }
    }
}