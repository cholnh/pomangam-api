package com.mrporter.pomangam.view.cart.service;

import com.mrporter.pomangam.orderEntry.cart.repository.CartRepositoryImpl;
import com.mrporter.pomangam.orderEntry.cartItem.repository.CartItemJpaRepository;
import com.mrporter.pomangam.view.cart.domain.CartViewDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    CartRepositoryImpl cartRepository;
    CartItemJpaRepository cartItemJpaRepository;

    @Override
    public CartViewDto getCartDto(Integer cart_idx) {
        CartViewDto dto = new CartViewDto();
        dto.setCartItems(cartItemJpaRepository.findByCartIdx(cart_idx));
        dto.setCartItemWithTimeMap(cartRepository.getCartWithArrivalTime(cart_idx));
        return dto;
    }
}
