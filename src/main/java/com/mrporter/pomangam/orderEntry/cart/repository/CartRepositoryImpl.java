package com.mrporter.pomangam.orderEntry.cart.repository;

import com.mrporter.pomangam.orderEntry.cart.domain.CartDto;
import com.mrporter.pomangam.orderEntry.cart.domain.CartInStoreQuantityDto;
import com.mrporter.pomangam.orderEntry.cartItem.repository.CartItemJpaRepository;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    @PersistenceContext
    EntityManager em;

    CartItemJpaRepository cartItemJpaRepository;
    JpaResultMapper jpaResultMapper;

    @Override
    public int countCart(Integer customer_idx) {
        Query nativeQuery = em
                .createNativeQuery("SELECT * FROM cart_tbl WHERE customer_idx = ?")
                .setParameter(1, customer_idx);

        List<CartDto> carts = jpaResultMapper.list(nativeQuery, CartDto.class);
        if(carts.isEmpty()) {
            return 0;
        }
        return cartItemJpaRepository.countByCartIdx(carts.get(0).getIdx());
    }

    @Override
    public CartDto getByIdx(Integer cart_Idx) {
        Query nativeQuery = em
                .createNativeQuery("SELECT * FROM cart_tbl where idx = ?")
                .setParameter(1, cart_Idx);
        List<CartDto> cartDtoList = jpaResultMapper.list(nativeQuery, CartDto.class);
        if(cartDtoList.isEmpty()) {
            return null;
        } else {
            return cartDtoList.get(0);
        }
    }

    @Override
    public CartDto getByCustomerIdx(Integer customer_idx) {
        Query nativeQuery = em
                .createNativeQuery("SELECT * FROM cart_tbl WHERE customer_idx = ?")
                .setParameter(1, customer_idx);
        List<CartDto> cartDtoList = jpaResultMapper.list(nativeQuery, CartDto.class);
        if(cartDtoList.isEmpty()) {
            return null;
        } else {
            return cartDtoList.get(0);
        }
    }

    @Override
    public List<CartInStoreQuantityDto> findCartInStoreQuantityByIdx(Integer cart_Idx) {
        Query nativeQuery2 = em
                .createNativeQuery(
                        "SELECT store_idx, sum(quantity) as quantity " +
                        "FROM item_for_cart_tbl " +
                        "where parent_item_idx is null " +
                        "and cart_idx = ? " +
                        "group by store_idx")
                .setParameter(1, cart_Idx);
        List<CartInStoreQuantityDto> dtoList = jpaResultMapper.list(nativeQuery2, CartInStoreQuantityDto.class);
        return dtoList;
    }
}