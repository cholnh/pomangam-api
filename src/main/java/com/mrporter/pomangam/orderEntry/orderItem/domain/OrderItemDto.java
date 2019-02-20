package com.mrporter.pomangam.orderEntry.orderItem.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class OrderItemDto implements Serializable {

    private Integer idx;

    private Integer order_idx;

    private Integer store_idx;

    private Integer product_idx;

    private Integer quantity;

    private String requirement;

    private Integer parent_item_idx;

    public OrderItem toEntity() {
        return null;
    }
}