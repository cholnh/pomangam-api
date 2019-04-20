package com.mrporter.pomangam.orderEntry.cartItem.controller;

import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemDto;
import com.mrporter.pomangam.orderEntry.cartItem.service.CartItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cartItems")
@RestController
@AllArgsConstructor
public class CartItemController {

    CartItemServiceImpl cartItemService;

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Integer cart_item_idx) {
        return new ResponseEntity<>(cartItemService.delete(cart_item_idx), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable(name = "id") Integer cart_item_idx,
                                   @RequestBody CartItemDto cartItem) {
        return new ResponseEntity<>(cartItemService.patch(cart_item_idx, cartItem), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") Integer cart_item_idx,
                                 @RequestBody CartItemDto cartItem) {
        return new ResponseEntity<>(cartItemService.update(cart_item_idx, cartItem), HttpStatus.OK);
    }
}
