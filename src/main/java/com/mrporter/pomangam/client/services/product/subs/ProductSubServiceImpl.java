package com.mrporter.pomangam.client.services.product.subs;

import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubDto;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategory;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategoryDto;
import com.mrporter.pomangam.client.repositories.product.sub.ProductSubJpaRepository;
import com.mrporter.pomangam.client.repositories.product.sub.category.ProductSubCategoryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductSubServiceImpl implements ProductSubService {

    ProductSubJpaRepository productSubRepo;
    ProductSubCategoryJpaRepository productSubCategoryRepo;

    @Override
    public List<ProductSubCategoryDto> findByIdxProduct(Long pIdx) {
        List<ProductSubCategory> productSubCategories = productSubRepo.findByIdxProductAndIsActiveIsTrue(pIdx);
        return ProductSubCategoryDto.fromEntities(productSubCategories);
    }

    @Override
    public List<ProductSubCategoryDto> findByIdxProductSubCategory(Long cIdx) {
        List<ProductSubCategory> productSubCategories = productSubCategoryRepo.findByIdxAndIsActiveIsTrue(cIdx);
        return ProductSubCategoryDto.fromEntities(productSubCategories);
    }

    @Override
    public ProductSubDto findByIdx(Long idx) {
        ProductSub entity = productSubRepo.findByIdxAndIsActiveIsTrue(idx);
        return ProductSubDto.fromEntity(entity);
    }

    @Override
    public long count() {
        return productSubRepo.countByIsActiveIsTrue();
    }

}
