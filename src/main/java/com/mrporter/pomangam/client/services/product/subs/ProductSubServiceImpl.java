package com.mrporter.pomangam.client.services.product.subs;

import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubDto;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategory;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategoryDto;
import com.mrporter.pomangam.client.repositories.product.sub.ProductSubJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductSubServiceImpl implements ProductSubService {

    ProductSubJpaRepository productSubRepo;

    @Override
    public List<ProductSubCategoryDto> findByIdxProduct(Long pIdx) {
        List<ProductSubCategory> productSubs = productSubRepo.findCategoryByIdxProductAndIsActiveIsTrue(pIdx);
        return ProductSubCategoryDto.fromEntities(productSubs);
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
