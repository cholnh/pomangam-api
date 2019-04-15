package com.mrporter.pomangam.orderEntry.cart.controller;

import com.mrporter.pomangam.orderEntry.cart.service.CartServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/carts")
@RestController
@AllArgsConstructor
public class CartController {

    CartServiceImpl cartService;

    @GetMapping("/search/countCart")
    public ResponseEntity<?> countCart(@RequestParam("customerIdx") Integer customerIdx) {
        return new ResponseEntity(cartService.countCart(customerIdx), HttpStatus.OK);
    }
}
