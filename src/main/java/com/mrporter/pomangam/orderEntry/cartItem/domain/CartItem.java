package com.mrporter.pomangam.orderEntry.cartItem.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "item_for_cart_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "cart_idx")
    private Integer cartIdx;

    @Column(name = "product_idx")
    private Integer productIdx;

    @Column(name = "store_idx")
    private Integer storeIdx;

    private Integer quantity;

    private String requirement;

    @Column(name = "parentItemIdx")
    private Integer parent_item_idx;

    @Builder
    public CartItem(Integer cartIdx, Integer productIdx, Integer storeIdx, Integer quantity, String requirement, Integer parent_item_idx) {
        this.cartIdx = cartIdx;
        this.productIdx = productIdx;
        this.storeIdx = storeIdx;
        this.quantity = quantity;
        this.requirement = requirement;
        this.parent_item_idx = parent_item_idx;
    }
}
