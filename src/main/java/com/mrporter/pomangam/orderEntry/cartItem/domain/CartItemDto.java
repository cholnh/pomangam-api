package com.mrporter.pomangam.orderEntry.cartItem.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CartItemDto implements Serializable {

    private Integer idx;

    private Integer cart_idx;

    private Integer product_idx;

    private Integer store_idx;

    private Integer quantity;

    private String requirement;

    private Integer parent_item_idx;
    public CartItem toEntity() {
        return null;
    }
}