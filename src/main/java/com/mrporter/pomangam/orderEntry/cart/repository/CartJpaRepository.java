package com.mrporter.pomangam.orderEntry.cart.repository;

import com.mrporter.pomangam.orderEntry.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CartJpaRepository extends JpaRepository<Cart, Integer>{
    Cart getByCustomerIdx(@Param("customerIdx") Integer customer_idx);
    Cart getByGuestIdx(@Param("guestIdx") Integer guest_idx);
    // 장바구니 클릭 시, 장바구니 내부에서 아이템들이 각각 언제언제 도착 가능한지 계산해야함.
}

