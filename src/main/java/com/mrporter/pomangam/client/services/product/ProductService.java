package com.mrporter.pomangam.client.services.product;

import com.mrporter.pomangam.client.domains.product.ProductDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDto> getByIdxStore(Long sidx, Pageable pageable);
}
