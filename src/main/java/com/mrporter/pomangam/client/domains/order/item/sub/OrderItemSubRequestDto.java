package com.mrporter.pomangam.client.domains.order.item.sub;

import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderItemSubRequestDto implements Serializable {

    private Long idxProductSub;
    private Short quantity;

    public OrderItemSub toEntity() {
        OrderItemSub entity = new ModelMapper().map(this, OrderItemSub.class);

        ProductSub productSub = ProductSub.builder().build();
        productSub.setIdx(this.idxProductSub);
        entity.setProductSub(productSub);

        return entity;
    }
}
