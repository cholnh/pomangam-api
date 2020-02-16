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
    public List<ProductSubDto> getByIdxProduct(Long pidx) {
        List<ProductSub> productSubs = productSubJpaRepository.getByIdxProduct(pidx);
        return ProductSubDto.fromEntities(productSubs);
    }


    public ProductSubDto getByIdx(Long idx) {
        ProductSub entity = productSubJpaRepository.findById(idx).get();
        return ProductSubDto.fromEntity(entity);
    }

    public long count() {
        return productSubJpaRepository.count();
    }

}
