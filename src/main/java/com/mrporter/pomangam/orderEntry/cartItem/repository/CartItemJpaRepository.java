package com.mrporter.pomangam.orderEntry.cartItem.repository;

import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = true)
public interface CartItemJpaRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCartIdx(@Param("cidx") Integer idx);
    int countByCartIdx(@Param("cidx") Integer idx);
}

