package com.mrporter.pomangam.test.data.productSubMapper;

import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubMapper;
import com.mrporter.pomangam.client.repositories.product.sub.ProductSubMapperJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductSubMapperData {

    @Autowired
    ProductSubMapperJpaRepository productSubMapperJpaRepository;

    @Transactional
    public void of(Long pIdx, Long ...subIdxes) {
        for(Long subIdx : subIdxes) {
            ProductSubMapper mapper = ProductSubMapper.builder()
                    .product(Product.builder().idx(pIdx).build())
                    .productSub(ProductSub.builder().idx(subIdx).build())
                    .build();
            productSubMapperJpaRepository.save(mapper);
        }
    }
}