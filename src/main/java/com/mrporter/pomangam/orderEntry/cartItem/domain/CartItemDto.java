package com.mrporter.pomangam.orderEntry.cartItem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class CartItemDto implements Serializable {

    private Integer idx;

    private Integer cartIdx;

    private Integer productIdx;

    private Integer storeIdx;

    private Integer quantity;

    private String requirement;

    private Integer parentItemIdx;

    private List<CartItemDto> subItems;

    public CartItemDto(Integer idx, Integer cartIdx, Integer productIdx, Integer storeIdx, Integer quantity, String requirement, Integer parentItemIdx) {
        this.idx = idx;
        this.cartIdx = cartIdx;
        this.productIdx = productIdx;
        this.storeIdx = storeIdx;
        this.quantity = quantity;
        this.requirement = requirement;
        this.parentItemIdx = parentItemIdx;
    }

    public CartItem toEntity() {
        return CartItem
                .builder()
                .idx(idx)
                .cartIdx(cartIdx)
                .productIdx(productIdx)
                .storeIdx(storeIdx)
                .quantity(quantity)
                .requirement(requirement)
                .parentItemIdx(parentItemIdx)
                .build();
    }
}