package com.mrporter.pomangam.orderEntry.cart.repository;

import com.mrporter.pomangam.orderEntry.cart.domain.CartDto;
import com.mrporter.pomangam.orderEntry.cart.domain.CartInStoreQuantityDto;
import com.mrporter.pomangam.orderEntry.cart.domain.CartViewDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository {
    int countCartByCustomerIdx(@Param("customerIdx") Integer customer_idx);

    List<CartInStoreQuantityDto> findCartInStoreQuantityByIdx(@Param("cartIdx") Integer cart_Idx);
    CartDto getByIdx(@Param("cartIdx") Integer cart_Idx);
    CartDto getByCustomerIdx(@Param("customerIdx") Integer customer_idx);
    CartDto getByGuestIdx(Integer guest_idx);

    int countCartByGuestIdx(Integer guestIdx);
    CartViewDto getCartDtoByGuestIdx(Integer guestIdx);
}
