package com.mrporter.pomangam.client.services.product;

import com.mrporter.pomangam.client.domains.product.ProductDto;
import com.mrporter.pomangam.client.domains.product.sub.ProductSummaryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductSummaryDto> findByIdxStore(Long sIdx, Pageable pageable);
    List<ProductSummaryDto> findByIdxProductCategory(Long cIdx, Pageable pageable);
    ProductDto findByIdx(Long idx);
    long countByIdxStore(Long sIdx);
    long countByIdxProductCategory(Long cIdx);
}
