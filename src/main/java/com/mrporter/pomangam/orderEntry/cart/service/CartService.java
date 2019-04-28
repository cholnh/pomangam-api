package com.mrporter.pomangam.orderEntry.cart.service;

import com.mrporter.pomangam.orderEntry.cart.domain.Cart;
import com.mrporter.pomangam.orderEntry.cart.domain.CartDto;
import com.mrporter.pomangam.orderEntry.cart.domain.CartTimeMapDto;
import com.mrporter.pomangam.orderEntry.cart.domain.CartViewDto;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemInputDto;

import java.time.ZoneId;
import java.util.List;

public interface CartService {
    int countCartByCustomerIdx(Integer customerIdx);

    Cart update(Integer cart_idx, CartDto dto);
    Cart patch(Integer cart_idx, CartDto dto);
    Boolean delete(Integer cart_idx);

    CartViewDto getCartDtoByCustomerIdx(Integer cart_idx);
    List<CartTimeMapDto> getCartWithArrivalTime(Integer cart_Idx, ZoneId zoneId);
    List<CartTimeMapDto> getCartWithArrivalTimeByCartIdx(Integer cart_Idx);
    List<CartTimeMapDto> getCartWithArrivalTimeByCustomerIdx(Integer customer_idx);
    Cart saveCart(Cart cart);
    Cart saveCartItemInput(CartItemInputDto cartItems);

    int countCartByGuestIdx(Integer guestIdx);
    CartViewDto getCartDtoByGuestIdx(Integer guestIdx);

    int getTotalAmount(Integer cartIdx);

    Boolean deleteByGuestIdx(Integer guestIdx);
    Boolean deleteByCustomerIdx(Integer customerIdx);
}
