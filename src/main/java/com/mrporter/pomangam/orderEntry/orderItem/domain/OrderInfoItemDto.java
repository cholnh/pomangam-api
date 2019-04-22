package com.mrporter.pomangam.orderEntry.orderItem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class OrderInfoItemDto implements Serializable {

    private Integer order_item_idx;

    private String product_name;

    private Integer quantity;

    private String requirement;

    private Integer parent_item_idx;

    private Integer unit_amount;

}