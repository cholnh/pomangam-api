package com.mrporter.pomangam.client.domains.user.order;

import com.mrporter.pomangam.client.domains.store.StoreDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class OrderDto implements Serializable {

    private Integer idx;

    private String categoryTitle;

    private List<StoreDto> stores;

    public OrderDto(Integer idx, String categoryTitle, List<StoreDto> stores) {
        this.idx = idx;
        this.categoryTitle = categoryTitle;
        this.stores = stores;
    }

    public static OrderDto fromEntity(Order entity) {
        return new OrderDto(
            entity.getIdx(),
            entity.getCategoryTitle(),
            StoreDto.fromEntities(entity.getStores())
        );
    }

    public static List<OrderDto> fromEntities(List<Order>entities) {
        List<OrderDto> dtos = new ArrayList<>();
        for(Order entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
