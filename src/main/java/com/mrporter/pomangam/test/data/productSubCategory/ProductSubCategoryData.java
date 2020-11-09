package com.mrporter.pomangam.test.data.productSubCategory;

import com.mrporter.pomangam.client.domains.product.sub.ProductSubType;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategory;
import com.mrporter.pomangam.client.repositories.product.sub.category.ProductSubCategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductSubCategoryData {

    @Autowired
    ProductSubCategoryJpaRepository productSubCategoryJpaRepository;

    @Transactional
    public void of(Long idx, String title, ProductSubType type, boolean isNecessary) {
        ProductSubCategory productSubCategory = ProductSubCategory.builder()
                .idx(idx)
                .categoryTitle(title)
                .productSubType(type)
                .isNecessary(isNecessary)
                .build();
        productSubCategoryJpaRepository.save(productSubCategory);
    }
}