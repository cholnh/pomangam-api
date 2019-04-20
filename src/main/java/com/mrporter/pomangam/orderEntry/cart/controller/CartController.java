package com.mrporter.pomangam.orderEntry.cart.controller;

import com.mrporter.pomangam.orderEntry.cart.domain.CartDto;
import com.mrporter.pomangam.orderEntry.cart.domain.CartViewDto;
import com.mrporter.pomangam.orderEntry.cart.service.CartServiceImpl;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemInputDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/carts")
@RestController
@AllArgsConstructor
public class CartController {

    CartServiceImpl cartService;

    @GetMapping
    public ResponseEntity<?> getView(@RequestParam("customerIdx") Integer customer_idx) {
        CartViewDto dto = cartService.getCartDto(customer_idx);
        if(dto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/search/countCart")
    public ResponseEntity<?> countCart(@RequestParam("customerIdx") Integer customerIdx) {
        return new ResponseEntity(cartService.countCart(customerIdx), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody CartItemInputDto cartItem) {
        cartService.saveCartItemInput(cartItem);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Integer cart_idx) {
        return new ResponseEntity<>(cartService.delete(cart_idx), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable(name = "id") Integer cart_idx,
                                   @RequestBody CartDto cartItem) {
        return new ResponseEntity<>(cartService.patch(cart_idx, cartItem), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") Integer cart_idx,
                                 @RequestBody CartDto cartItem) {
        return new ResponseEntity<>(cartService.update(cart_idx, cartItem), HttpStatus.OK);
    }
}
