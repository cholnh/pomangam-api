package com.mrporter.pomangam.client.services.product.subs;

import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubDto;
import com.mrporter.pomangam.client.repositories.product.sub.ProductSubJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductSubServiceImpl implements ProductSubService {

    ProductSubJpaRepository productSubJpaRepository;

    @Override
    public List<ProductSubDto> findByIdxProduct(Long pIdx) {
        List<ProductSub> productSubs = productSubJpaRepository.findByIdxProductAndIsActiveIsTrue(pIdx);
        return ProductSubDto.fromEntities(productSubs);
    }

    @Override
    public ProductSubDto findByIdx(Long idx) {
        ProductSub entity = productSubJpaRepository.findByIdxAndIsActiveIsTrue(idx);
        return ProductSubDto.fromEntity(entity);
    }

    @Override
    public long count() {
        return productSubJpaRepository.countByIsActiveIsTrue();
    }

}
