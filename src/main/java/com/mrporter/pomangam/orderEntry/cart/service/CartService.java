package com.mrporter.pomangam.orderEntry.cart.service;

import com.mrporter.pomangam.orderEntry.cart.domain.Cart;
import com.mrporter.pomangam.orderEntry.cart.domain.CartDto;
import com.mrporter.pomangam.orderEntry.cart.domain.CartTimeMapDto;
import com.mrporter.pomangam.orderEntry.cart.domain.CartViewDto;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemInputDto;

import java.time.ZoneId;
import java.util.List;

public interface CartService {
    int countCart(Integer customerIdx);

    Cart update(Integer cart_idx, CartDto dto);
    Cart patch(Integer cart_idx, CartDto dto);
    Boolean delete(Integer cart_idx);

    CartViewDto getCartDto(Integer cart_idx);
    List<CartTimeMapDto> getCartWithArrivalTime(Integer cart_Idx, ZoneId zoneId);
    List<CartTimeMapDto> getCartWithArrivalTimeByCartIdx(Integer cart_Idx);
    List<CartTimeMapDto> getCartWithArrivalTimeByCustomerIdx(Integer customer_idx);
    Cart saveCart(Cart cart);
    void saveCartItemInput(CartItemInputDto cartItems);
}
