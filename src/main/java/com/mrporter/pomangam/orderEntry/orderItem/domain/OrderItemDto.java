package com.mrporter.pomangam.orderEntry.orderItem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class OrderItemDto implements Serializable {

    private Integer idx;

    private Integer order_idx;

    private Integer store_idx;

    private Integer product_idx;

    private Integer quantity;

    private String requirement;

    private Integer parent_item_idx;

    private Integer unit_amount;

    public OrderItemDto(Integer idx, Integer order_idx, Integer store_idx, Integer product_idx, Integer quantity, String requirement, Integer parent_item_idx, Integer unit_amount) {
        this.idx = idx;
        this.order_idx = order_idx;
        this.store_idx = store_idx;
        this.product_idx = product_idx;
        this.quantity = quantity;
        this.requirement = requirement;
        this.parent_item_idx = parent_item_idx;
        this.unit_amount = unit_amount;
    }
}