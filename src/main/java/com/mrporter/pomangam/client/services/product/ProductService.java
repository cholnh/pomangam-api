package com.mrporter.pomangam.client.services.product;

import com.mrporter.pomangam.client.domains.product.ProductDto;
import com.mrporter.pomangam.client.domains.product.ProductSummaryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductSummaryDto> findSummaryByIdxStore(Long sIdx, Pageable pageable);
    List<ProductSummaryDto> findSummaryByIdxProductCategory(Long cIdx, Pageable pageable);
    ProductDto findByIdx(Long idx, String phoneNumber);
    long countByIdxStore(Long sIdx);
    long countByIdxProductCategory(Long cIdx);
}
