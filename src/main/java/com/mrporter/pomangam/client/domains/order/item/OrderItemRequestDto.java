package com.mrporter.pomangam.client.domains.order.item;

import com.mrporter.pomangam.client.domains.order.item.sub.OrderItemSub;
import com.mrporter.pomangam.client.domains.order.item.sub.OrderItemSubRequestDto;
import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.store.Store;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemRequestDto implements Serializable {

    private Long idxStore;
    private Long idxProduct;
    private Short quantity;
    private String requirement;
    List<OrderItemSubRequestDto> orderItemSubs = new ArrayList<>();

    public OrderItem toEntity() {
        OrderItem entity = OrderItem.builder()
                .store(Store.builder().idx(this.idxStore).build())
                .product(Product.builder().idx(this.idxProduct).build())
                .quantity(this.quantity)
                .requirement(this.requirement)
                .orderItemSubs(convertOrderItem(this.orderItemSubs))
                .build();
        return entity;
    }

    private List<OrderItemSub> convertOrderItem(List<OrderItemSubRequestDto> dtos) {
        List<OrderItemSub> entities = new ArrayList<>();
        if(dtos != null) {
            for(OrderItemSubRequestDto dto : dtos) {
                if(dto != null) {
                    entities.add(dto.toEntity());
                }
            }
        }
        return entities;
    }
}
