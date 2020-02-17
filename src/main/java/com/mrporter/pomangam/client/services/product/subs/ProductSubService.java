package com.mrporter.pomangam.client.services.product.subs;

import com.mrporter.pomangam.client.domains.product.sub.ProductSubDto;

import java.util.List;

public interface ProductSubService {
    List<ProductSubDto> findByIdxProduct(Long pIdx);
    ProductSubDto findByIdx(Long idx);
    long count();
}
