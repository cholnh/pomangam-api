package com.mrporter.pomangam.orderEntry.cart.service;

import com.mrporter.pomangam.orderEntry.cart.repository.CartRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    CartRepositoryImpl cartRepository;

    public int countCart(@RequestParam("customerIdx") Integer customerIdx) {
        return cartRepository.countCart(customerIdx);
    }
}
