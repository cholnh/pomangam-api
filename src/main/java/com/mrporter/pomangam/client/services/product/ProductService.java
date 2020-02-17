package com.mrporter.pomangam.client.services.product;

import com.mrporter.pomangam.client.domains.product.ProductDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDto> findByIdxStore(Long sIdx, Pageable pageable);
    ProductDto findByIdx(Long idx);
    long count();
}
