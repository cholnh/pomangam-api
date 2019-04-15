package com.mrporter.pomangam.view.cart.service;

import com.mrporter.pomangam.orderEntry.cart.domain.Cart;
import com.mrporter.pomangam.orderEntry.cart.repository.CartJpaRepository;
import com.mrporter.pomangam.orderEntry.cart.repository.CartRepositoryImpl;
import com.mrporter.pomangam.orderEntry.cartItem.repository.CartItemJpaRepository;
import com.mrporter.pomangam.view.cart.domain.CartViewDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartViewServiceImpl implements CartViewService {

    CartRepositoryImpl cartRepository;
    CartJpaRepository cartJpaRepository;
    CartItemJpaRepository cartItemJpaRepository;

    @Override
    public CartViewDto getCartDto(Integer customer_idx) {
        CartViewDto dto = new CartViewDto();
        Cart cart = cartJpaRepository.getByCustomerIdx(customer_idx);
        dto.setCartItems(cartItemJpaRepository.findByCartIdx(cart.getIdx()));
        dto.setCartItemWithTimeMap(cartRepository.getCartWithArrivalTimeByCartIdx(cart.getIdx()));
        return dto;
    }
}
