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

    @GetMapping("/search/getViewByCustomerIdx")
    public ResponseEntity<?> getViewByCustomerIdx(@RequestParam("customerIdx") Integer customer_idx) {
        CartViewDto dto = cartService.getCartDtoByCustomerIdx(customer_idx);
        if(dto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/search/getViewByGuestIdx")
    public ResponseEntity<?> getViewByGuestIdx(@RequestParam("guestIdx") Integer guestIdx) {
        CartViewDto dto = cartService.getCartDtoByGuestIdx(guestIdx);
            if(dto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/search/countCartByCustomerIdx")
    public ResponseEntity<?> countCartByCustomerIdx(@RequestParam("customerIdx") Integer customerIdx) {
        return new ResponseEntity(cartService.countCartByCustomerIdx(customerIdx), HttpStatus.OK);
    }

    @GetMapping("/search/countCartByGuestIdx")
    public ResponseEntity<?> countCartByGuestIdx(@RequestParam("guestIdx") Integer guestIdx) {
        return new ResponseEntity(cartService.countCartByGuestIdx(guestIdx), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody CartItemInputDto cartItem) {
        return new ResponseEntity(cartService.saveCartItemInput(cartItem), HttpStatus.OK);
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