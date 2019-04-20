package com.mrporter.pomangam.orderEntry.cartItem.service;

import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItem;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemDto;

public interface CartItemService {
    CartItem update(Integer cart_item_idx, CartItemDto dto);
    CartItem patch(Integer cart_item_idx, CartItemDto dto);
    Boolean delete(Integer cart_item_idx);
}
