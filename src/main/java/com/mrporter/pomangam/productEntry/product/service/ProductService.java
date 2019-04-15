package com.mrporter.pomangam.productEntry.product.service;

import com.mrporter.pomangam.productEntry.product.domain.ProductWithCostDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService {
    List<ProductWithCostDto> findByStoreIdx(@Param("storeIdx") Integer store_idx);
    ProductWithCostDto findByProductIdx(@Param("productIdx") Integer product_idx);
}
