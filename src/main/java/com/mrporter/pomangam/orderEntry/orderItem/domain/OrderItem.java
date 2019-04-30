package com.mrporter.pomangam.orderEntry.orderItem.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "item_for_order_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer order_idx;

    private Integer store_idx;

    private Integer product_idx;

    private Integer quantity;

    private String requirement;

    private Integer parent_item_idx;

    private Integer unit_amount;

    @Builder
    public OrderItem(Integer idx, Integer order_idx, Integer store_idx, Integer product_idx, Integer quantity, String requirement, Integer parent_item_idx, Integer unit_amount) {
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
