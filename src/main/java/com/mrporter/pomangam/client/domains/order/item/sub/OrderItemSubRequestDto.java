package com.mrporter.pomangam.client.domains.order.item.sub;

import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderItemSubRequestDto implements Serializable {

    private Long idxProductSub;
    private Short quantity;

    public OrderItemSub toEntity() {
        OrderItemSub entity = OrderItemSub.builder()
                .productSub(ProductSub.builder().idx(this.idxProductSub).build())
                .quantity(this.quantity)
                .build();
        return entity;
    }
}
