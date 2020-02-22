package com.mrporter.pomangam.client.services.product;

import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.product.ProductDto;
import com.mrporter.pomangam.client.repositories.product.ProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductJpaRepository productRepo;

    @Override
    public List<ProductDto> findByIdxStore(Long sIdx, Pageable pageable) {
        List<Product> products = productRepo.findByIdxStoreAndIsActiveIsTrueOrderBySequenceAsc(sIdx, pageable).getContent();
        return ProductDto.fromEntities(products);
    }

    @Override
    public ProductDto findByIdx(Long idx) {
        Product entity = productRepo.findByIdxAndIsActiveIsTrue(idx);
        return ProductDto.fromEntity(entity);
    }

    @Override
    public long count() {
        return productRepo.countByIsActiveIsTrue();
    }

}
