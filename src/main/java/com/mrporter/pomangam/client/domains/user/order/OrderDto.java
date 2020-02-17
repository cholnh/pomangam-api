package com.mrporter.pomangam.client.domains.user.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    public Order toEntity() {
        Order entity = new ModelMapper().map(this, Order.class);
        return entity;
    }

    public static OrderDto fromEntity(Order entity) {
        if(entity == null) return null;
        OrderDto dto = new ModelMapper().map(entity, OrderDto.class);
        return dto;
    }

    public static List<OrderDto> fromEntities(List<Order> entities) {
        if(entities == null) return null;
        List<OrderDto> dtos = new ArrayList<>();
        for(Order entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
