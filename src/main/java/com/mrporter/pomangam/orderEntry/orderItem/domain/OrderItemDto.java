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

    private Integer unit_total;

    private String requirement;

    public OrderItem toEntity() {
        return null;
    }
}