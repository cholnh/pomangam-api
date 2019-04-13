package com.mrporter.pomangam.productEntry.product.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

public interface ProductRepository {
    ResponseEntity<?> findByStoreIdx(@Param("storeIdx") Integer store_idx);
    ResponseEntity<?> findByProductIdx(@Param("productIdx") Integer product_idx);
}
