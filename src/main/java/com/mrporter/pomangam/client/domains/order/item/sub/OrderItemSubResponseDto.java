package com.mrporter.pomangam.client.domains.order.item.sub;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemSubResponseDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String nameProductSub;
    private Long idxProductSub;
    private int saleCost;
    private Short quantity;

    public static OrderItemSubResponseDto fromEntity(OrderItemSub entity) {
        if(entity == null) return null;
        OrderItemSubResponseDto dto = new ModelMapper().map(entity, OrderItemSubResponseDto.class);

        dto.setNameProductSub("("+entity.getProductSub().getProductSubCategory().getCategoryTitle()+") "+entity.getProductSub().getProductSubInfo().getName());
        dto.setIdxProductSub(entity.getProductSub().getIdx());
        dto.setSaleCost(entity.getProductSub().getCost().saleCost());

        return dto;
    }

    public static List<OrderItemSubResponseDto> fromEntities(List<OrderItemSub> entities) {
        if(entities == null) return null;
        List<OrderItemSubResponseDto> dtos = new ArrayList<>();
        for(OrderItemSub entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
