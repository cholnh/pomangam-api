package com.mrporter.pomangam.client.domains.ordertime;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderTimeDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    public OrderTime toEntity() {
        OrderTime entity = new ModelMapper().map(this, OrderTime.class);
        return entity;
    }

    public static OrderTimeDto fromEntity(OrderTime entity) {
        if(entity == null) return null;
        OrderTimeDto dto = new ModelMapper().map(entity, OrderTimeDto.class);
        return dto;
    }

    public static List<OrderTimeDto> fromEntities(List<OrderTime> entities) {
        if(entities == null) return null;
        List<OrderTimeDto> dtos = new ArrayList<>();
        for(OrderTime entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}