package com.mrporter.pomangam.productEntry.product.service;

import com.mrporter.pomangam.productEntry.product.domain.DetailOrderDto;
import com.mrporter.pomangam.productEntry.product.domain.ProductWithCostDto;
import com.mrporter.pomangam.productEntry.product.domain.SearchProductDto;
import com.mrporter.pomangam.productEntry.product.repository.ProductRepositoryImpl;
import com.mrporter.pomangam.storeEntry.store.repository.StoreRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRepositoryImpl productRepository;
    StoreRepositoryImpl storeRepository;

    @Override
    public List<ProductWithCostDto> findByStoreIdx(Integer store_idx, Integer type, String orderBy) {
        return productRepository.findByStoreIdx(store_idx, type, orderBy);
    }

    @Override
    public ProductWithCostDto findByProductIdx(Integer product_idx) {
        return productRepository.findByProductIdx(product_idx);
    }

    @Override
    public SearchProductDto findByQuery(String query, Integer delivery_site_idx) {
        SearchProductDto dto = new SearchProductDto();

        dto.setProductSummaryDtoList(productRepository.findByQuery(query, delivery_site_idx));
        dto.setStoreDtoList(storeRepository.findByQuery(query, delivery_site_idx));

        return dto;
    }

    @Override
    public DetailOrderDto getDetailOrder(Integer productIdx) {
        DetailOrderDto dto = new DetailOrderDto();

        dto.setProductWithCostDto(productRepository.findByProductIdx(productIdx));
        dto.setSubMenuList(productRepository.findAdditionalByType(productIdx, 1));
        dto.setToppingMenuList(productRepository.findAdditionalByType(productIdx, 2));
        dto.setBeverageMenuList(productRepository.findAdditionalByType(productIdx, 3));

        return dto;
    }
}
