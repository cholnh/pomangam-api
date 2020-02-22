package com.mrporter.pomangam.client.domains.order.item;

import com.mrporter.pomangam.client.domains.order.item.sub.OrderItemSub;
import com.mrporter.pomangam.client.domains.order.item.sub.OrderItemSubRequestDto;
import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.store.Store;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

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
        OrderItem entity = new ModelMapper().map(this, OrderItem.class);

        // 업체
        Store store = Store.builder().build();
        store.setIdx(this.idxStore);
        entity.setStore(store);

        // 제품
        Product product = Product.builder().build();
        product.setIdx(this.idxProduct);
        entity.setProduct(product);

        // 서브 제품
        entity.setOrderItemSubs(convertOrderItem(this.orderItemSubs));

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
