package com.mrporter.pomangam.view.cart.domain;

import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
public class CartViewDto implements Serializable {
    List<CartItem> cartItems;
    Map<Integer, LocalDateTime>  cartItemWithTimeMap;

}
