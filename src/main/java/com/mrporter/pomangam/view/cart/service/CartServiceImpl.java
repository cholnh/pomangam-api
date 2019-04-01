package com.mrporter.pomangam.view.cart.service;

import com.mrporter.pomangam.orderEntry.cart.repository.CartRepositoryImpl;
import com.mrporter.pomangam.view.cart.domain.CartViewDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    CartRepositoryImpl cartRepository;

    @Override
    public CartViewDto getCartDto() {



        return null;
    }
}
