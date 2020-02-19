package com.mrporter.pomangam.client.domains.order.item;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    public OrderItem toEntity() {
        OrderItem entity = new ModelMapper().map(this, OrderItem.class);
        return entity;
    }

    public static OrderItemDto fromEntity(OrderItem entity) {
        if(entity == null) return null;
        OrderItemDto dto = new ModelMapper().map(entity, OrderItemDto.class);
        return dto;
    }

    public static List<OrderItemDto> fromEntities(List<OrderItem> entities) {
        if(entities == null) return null;
        List<OrderItemDto> dtos = new ArrayList<>();
        for(OrderItem entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
