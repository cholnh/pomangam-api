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

    @Column(name = "parent_item_idx")
    private Integer parentItemIdx;

    @Builder
    public CartItem(Integer idx, Integer cartIdx, Integer productIdx, Integer storeIdx, Integer quantity, String requirement, Integer parentItemIdx) {
        this.idx = idx;
        this.cartIdx = cartIdx;
        this.productIdx = productIdx;
        this.storeIdx = storeIdx;
        this.quantity = quantity;
        this.requirement = requirement;
        this.parentItemIdx = parentItemIdx;
    }
}
