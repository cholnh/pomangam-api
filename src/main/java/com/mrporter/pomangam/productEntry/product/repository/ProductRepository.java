package com.mrporter.pomangam.productEntry.product.repository;

import com.mrporter.pomangam.productEntry.product.domain.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository {
    List<ProductWithCostDto> findByStoreIdx(@Param("storeIdx") Integer store_idx,
                                            @Param("type") Integer type,
                                            @Param("orderby") String orderby,
                                            PageRequest pageRequest);
    ProductWithCostDto findByProductIdx(@Param("productIdx") Integer product_idx);
    List<ProductSummaryDto> findByQuery(String query, Integer delivery_site_idx);
    List<AdditionalDto> findAdditionalByType(Integer productIdx, Integer type);
    List<CategoryDto> findCategory(Integer store_idx);
}
