package com.mrporter.pomangam.productEntry.product.repository;

import com.mrporter.pomangam.productEntry.product.domain.ProductWithCostDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository {
    List<ProductWithCostDto> findByStoreIdx(@Param("storeIdx") Integer store_idx);
    ProductWithCostDto findByProductIdx(@Param("productIdx") Integer product_idx);
}
