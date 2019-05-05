package com.mrporter.pomangam.productEntry.product.service;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.service.UserService;
import com.mrporter.pomangam.feedbackHistory.likeForProduct.repository.LikeForProductRepositoryImpl;
import com.mrporter.pomangam.productEntry.product.domain.DetailOrderDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
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
    LikeForProductRepositoryImpl likeForProductRepository;
    UserService userService;

    @Override
    public List<ProductWithCostDto> findByStoreIdx(Integer store_idx, Integer type, String orderBy, PageRequest pageRequest) {
        if(store_idx == null) {
            return null;
        }
        if(pageRequest == null) {
            pageRequest = new PageRequest(0, 10);
        }
        return productRepository.findByStoreIdx(store_idx, type, orderBy, pageRequest);
    }

    @Override
    public ProductWithCostDto findByProductIdx(Integer product_idx, String customerId) {
        if(product_idx == null) {
            return null;
        }
        User user = userService.findById(customerId);
        ProductWithCostDto dto = productRepository.findByProductIdx(product_idx);
        if(user != null) {
            dto.setLikeType(likeForProductRepository.getType(product_idx, user.getIdx()));
        }
        return dto;
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

    @Override
    public List<ProductWithCostDto> findByCategoryId(Integer store_idx, Integer categoryId, Integer type, String orderBy, PageRequest pageRequest) {
        if(pageRequest == null) {
            pageRequest = new PageRequest(0, 10);
        }
        List<ProductWithCostDto> products = productRepository.findByCategoryId(store_idx, categoryId, type, orderBy, pageRequest);
        return products;
    }

    @Override
    public void like(Integer productIdx, String customerId) {
        final User user = userService.findById(customerId);
        if(user != null) {
            likeForProductRepository.like(productIdx, user.getIdx());
        }
    }

    @Override
    public void unlike(Integer productIdx, String customerId) {
        final User user = userService.findById(customerId);
        if(user != null) {
            likeForProductRepository.unlike(productIdx, user.getIdx());
        }
    }
}
