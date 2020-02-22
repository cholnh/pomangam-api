package com.mrporter.pomangam.client.domains.order;

import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.order.item.OrderItem;
import com.mrporter.pomangam.client.domains.order.item.OrderItemRequestDto;
import com.mrporter.pomangam.client.domains.order.orderer.Orderer;
import com.mrporter.pomangam.client.domains.order.payment_info.PaymentInfo;
import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequestDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private OrderType orderType;
    private Short boxNumber;
    private Orderer orderer;
    private PaymentInfo paymentInfo;

    private Long idxOrderTime;
    private Long idxDeliveryDetailSite;

    List<OrderItemRequestDto> orderItems = new ArrayList<>();

    public Order toEntity() {
        Order entity = new ModelMapper().map(this, Order.class);

        // 주문 시간
        OrderTime orderTime = OrderTime.builder().build();
        orderTime.setIdx(this.idxOrderTime);
        entity.setOrderTime(orderTime);

        // 받는 장소
        DeliveryDetailSite deliveryDetailSite = DeliveryDetailSite.builder().build();
        deliveryDetailSite.setIdx(idxDeliveryDetailSite);
        entity.setDeliveryDetailSite(deliveryDetailSite);

        // 주문 내역
        entity.setOrderItems(convertOrderItem(this.orderItems));

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