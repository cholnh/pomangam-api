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

    ProductJpaRepository productJpaRepository;

    public List<ProductDto> getByIdxStore(Long sidx, Pageable pageable) {
        List<Product> products = productJpaRepository.findByIdxStoreOrderBySequenceAsc(sidx, pageable).getContent();
        return ProductDto.fromEntities(products);
    }


    public ProductDto getByIdx(Long idx) {
        Product entity = productJpaRepository.findById(idx).get();
        return ProductDto.fromEntity(entity);
    }

    public long count() {
        return productJpaRepository.count();
    }

}
