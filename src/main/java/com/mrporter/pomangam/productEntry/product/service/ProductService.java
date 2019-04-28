package com.mrporter.pomangam.productEntry.product.service;

import com.mrporter.pomangam.productEntry.product.domain.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService {
    List<ProductWithCostDto> findByStoreIdx(@Param("storeIdx") Integer store_idx,
                                            @Param("type") Integer type,
                                            @Param("orderBy") String orderBy,
                                            PageRequest pageRequest);
    ProductWithCostDto findByProductIdx(@Param("productIdx") Integer product_idx);
    SearchProductDto findByQuery(String query, Integer delivery_site_idx);
    DetailOrderDto getDetailOrder(Integer productIdx);
    List<ProductWithCostDto> findByCategoryId(Integer store_idx, Integer categoryId, Integer type, String orderBy, PageRequest pageRequest);
}
