package com.mrporter.pomangam.view.cart.service;

import com.mrporter.pomangam.view.cart.domain.CartViewDto;

public interface CartViewService {
    CartViewDto getCartDto(Integer cart_idx);
}
