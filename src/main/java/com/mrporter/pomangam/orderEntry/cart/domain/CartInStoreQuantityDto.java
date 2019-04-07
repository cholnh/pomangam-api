package com.mrporter.pomangam.orderEntry.cart.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CartInStoreQuantityDto implements Serializable {

    private Integer store_idx;

    private Integer quantity;

    public CartInStoreQuantityDto(Integer store_idx, BigDecimal quantity) {
        this.store_idx = store_idx;
        this.quantity = quantity==null?0:quantity.intValue();
    }
}