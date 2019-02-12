package com.mrporter.pomangam.orderEntry.orderItem.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "order_item_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer order_idx;

    private Integer store_idx;;

    private Integer product_idx;

    private Integer quantity;

    private Integer unit_total;

    private String requirement;

    @Builder
    public OrderItem(Integer order_idx, Integer store_idx, Integer product_idx, Integer quantity, Integer unit_total, String requirement) {
        this.order_idx = order_idx;
        this.store_idx = store_idx;
        this.product_idx = product_idx;
        this.quantity = quantity;
        this.unit_total = unit_total;
        this.requirement = requirement;
    }
}
