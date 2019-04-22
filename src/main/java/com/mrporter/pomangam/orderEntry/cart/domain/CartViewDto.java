package com.mrporter.pomangam.orderEntry.cart.domain;

import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemViewDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class CartViewDto implements Serializable {
    CartDto cart;
    List<CartItemViewDto> cartItems;
    List<CartTimeMapDto> cartTimeMapDtoList;
}
