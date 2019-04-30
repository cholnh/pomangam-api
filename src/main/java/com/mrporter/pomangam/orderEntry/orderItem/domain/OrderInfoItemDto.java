package com.mrporter.pomangam.orderEntry.orderItem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderInfoItemDto implements Serializable {

    private Integer product_idx;

    private String product_name;

    private Integer quantity;

    private String requirement;

    private Integer parent_item_idx;

    private Integer unit_amount;

}