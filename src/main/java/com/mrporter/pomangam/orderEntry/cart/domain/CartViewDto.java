package com.mrporter.pomangam.orderEntry.cart.domain;

import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class CartViewDto implements Serializable {
    CartDto cart;
    List<CartItemDto> cartItems;
    List<CartTimeMapDto> cartTimeMapDtoList;
}
