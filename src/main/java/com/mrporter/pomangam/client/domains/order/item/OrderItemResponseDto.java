package com.mrporter.pomangam.client.domains.order.item;

import com.mrporter.pomangam.client.domains.order.item.sub.OrderItemSub;
import com.mrporter.pomangam.client.domains.order.item.sub.OrderItemSubResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemResponseDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String nameStore;
    private String nameProduct;
    private int saleCost;
    private Short quantity;
    private String requirement;

    List<OrderItemSubResponseDto> orderItemSubs = new ArrayList<>();

    public static OrderItemResponseDto fromEntity(OrderItem entity) {
        if(entity == null) return null;
        OrderItemResponseDto dto = new ModelMapper().map(entity, OrderItemResponseDto.class);

        dto.setNameStore(entity.getStore().getStoreInfo().getName());
        dto.setNameProduct(entity.getProduct().getProductInfo().getName());
        dto.setSaleCost(entity.getProduct().getCost().saleCost());

        dto.setOrderItemSubs(convertOrderItemSub(entity.getOrderItemSubs()));

        return dto;
    }

    public static List<OrderItemResponseDto> fromEntities(List<OrderItem> entities) {
        if(entities == null) return null;
        List<OrderItemResponseDto> dtos = new ArrayList<>();
        for(OrderItem entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }

    private static List<OrderItemSubResponseDto> convertOrderItemSub(List<OrderItemSub> orderItemSubs) {
        List<OrderItemSubResponseDto> dtos = new ArrayList<>();
        if(orderItemSubs != null) {
            for(OrderItemSub orderItemSub : orderItemSubs) {
                if(orderItemSub != null) {
                    dtos.add(OrderItemSubResponseDto.fromEntity(orderItemSub));
                }
            }
        }
        return dtos;
    }
}
