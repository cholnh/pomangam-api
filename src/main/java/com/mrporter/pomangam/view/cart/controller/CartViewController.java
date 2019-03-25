package com.mrporter.pomangam.view.cart.controller;

import com.mrporter.pomangam.view.cart.service.CartServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/views/cart")
@RestController
@AllArgsConstructor
public class CartViewController {

    CartServiceImpl cartService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam("didx") Integer delivery_site_idx) {
        return new ResponseEntity<>(cartService.getCartDto(), HttpStatus.OK);
    }
}
