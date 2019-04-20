package com.mrporter.pomangam.orderEntry.cartItem.service;

import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItem;
import com.mrporter.pomangam.orderEntry.cartItem.domain.CartItemDto;
import com.mrporter.pomangam.orderEntry.cartItem.repository.CartItemJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    CartItemJpaRepository cartItemJpaRepository;

    @Override
    public CartItem update(Integer cart_item_idx, CartItemDto dto) {
        final CartItem fetched = cartItemJpaRepository.getOne(cart_item_idx);
        if (fetched == null) {
            return null;
        }

        fetched.setCartIdx(dto.getCartIdx());
        fetched.setProductIdx(dto.getProductIdx());
        fetched.setStoreIdx(dto.getStoreIdx());
        fetched.setQuantity(dto.getQuantity());
        fetched.setRequirement(dto.getRequirement());
        fetched.setParentItemIdx(dto.getParentItemIdx());

        return cartItemJpaRepository.save(fetched);
    }

    @Override
    public CartItem patch(Integer cart_item_idx, CartItemDto dto) {
        final CartItem fetched = cartItemJpaRepository.getOne(cart_item_idx);
        if (fetched == null) {
            return null;
        }

        if (dto.getCartIdx() != null) {
            fetched.setCartIdx(dto.getCartIdx());
        }
        if (dto.getProductIdx() != null) {
            fetched.setProductIdx(dto.getProductIdx());
        }
        if (dto.getStoreIdx() != null) {
            fetched.setStoreIdx(dto.getStoreIdx());
        }
        if (dto.getQuantity() != null) {
            fetched.setQuantity(dto.getQuantity());
        }
        if (dto.getRequirement() != null) {
            fetched.setRequirement(dto.getRequirement());
        }
        if (dto.getParentItemIdx() != null) {
            fetched.setParentItemIdx(dto.getParentItemIdx());
        }

        return cartItemJpaRepository.save(fetched);
    }

    @Override
    public Boolean delete(Integer cart_item_idx) {
        final CartItem fetched = cartItemJpaRepository.getOne(cart_item_idx);
        if (fetched == null) {
            return false;
        } else {
            cartItemJpaRepository.delete(fetched);
            return true;
        }
    }
}
