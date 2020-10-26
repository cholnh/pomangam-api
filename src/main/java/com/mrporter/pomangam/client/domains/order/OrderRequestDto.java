package com.mrporter.pomangam.client.domains.order;

import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.order.cash_receipt.CashReceiptType;
import com.mrporter.pomangam.client.domains.order.item.OrderItem;
import com.mrporter.pomangam.client.domains.order.item.OrderItemRequestDto;
import com.mrporter.pomangam.client.domains.order.orderer.Orderer;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.domains.order.payment_info.PaymentInfo;
import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import com.mrporter.pomangam.client.domains.payment.PaymentType;
import com.mrporter.pomangam.client.domains.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString(exclude = {"user"})
public class OrderRequestDto implements Serializable {

    // 주문 날짜
    private LocalDate orderDate;

    // 주문 시간
    private Long idxOrderTime;

    // 받는 장소
    private Long idxDeliveryDetailSite;

    private Long idxFcmToken;

    // PaymentInfo
    private PaymentType paymentType;
    private Integer usingPoint;
    private String usingCouponCode;
    private Set<Long> idxesUsingCoupons = new HashSet<>();
    private Set<Long> idxesUsingPromotions = new HashSet<>();
    private String cashReceipt;
    private CashReceiptType cashReceiptType;

    List<OrderItemRequestDto> orderItems = new ArrayList<>();

    // Orderer
    private OrdererType ordererType;
    private User user;  // 내부 작성용

    private String vbankName;

    public Order toEntity() {
        Order entity = Order.builder()
                .orderType(OrderType.PAYMENT_READY)
                .orderDate(this.orderDate)
                .orderTime(OrderTime.builder()
                        .idx(this.idxOrderTime)
                        .build())
                .deliveryDetailSite(DeliveryDetailSite.builder()
                        .idx(this.idxDeliveryDetailSite)
                        .build())
                .orderer(Orderer.builder()
                        .ordererType(this.ordererType)
                        .idxFcmToken(this.idxFcmToken)
                        .user(this.user)
                        .build())
                .paymentInfo(PaymentInfo.builder()
                        .paymentType(this.paymentType)
                        .usingPoint(this.usingPoint)
                        .cashReceipt(this.cashReceipt)
                        .cashReceiptType(this.cashReceiptType)
                        .build())
                .orderItems(convertOrderItem(this.orderItems))
                .build();
        return entity;
    }

    private List<OrderItem> convertOrderItem(List<OrderItemRequestDto> dtos) {
        List<OrderItem> entities = new ArrayList<>();
        if(dtos != null) {
            for(OrderItemRequestDto dto : dtos) {
                if(dto != null) {
                    entities.add(dto.toEntity());
                }
            }
        }
        return entities;
    }
}