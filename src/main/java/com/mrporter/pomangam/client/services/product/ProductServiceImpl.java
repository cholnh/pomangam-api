package com.mrporter.pomangam.client.services.product;

import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.product.ProductDto;
import com.mrporter.pomangam.client.domains.product.ProductSummaryDto;
import com.mrporter.pomangam.client.repositories.product.ProductJpaRepository;
import com.mrporter.pomangam.client.repositories.product.like.ProductLikeJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductJpaRepository productRepo;
    UserJpaRepository userRepo;
    ProductLikeJpaRepository productLikeRepo;

    @Override
    public List<ProductSummaryDto> findByIdxStore(Long sIdx, Pageable pageable) {
        List<Product> products = productRepo.findByIdxStoreAndIsActiveIsTrueOrderBySequenceAsc(sIdx, pageable).getContent();
        return ProductSummaryDto.fromEntities(products);
    }

    @Override
    public List<ProductSummaryDto> findByIdxProductCategory(Long cIdx, Pageable pageable) {
        List<Product> products = productRepo.findByProductCategory_IdxAndIsActiveIsTrueOrderBySequenceAsc(cIdx, pageable).getContent();
        return ProductSummaryDto.fromEntities(products);
    }

    @Override
    public ProductDto findByIdx(Long idx, String phoneNumber) {
        Product entity = productRepo.findByIdxAndIsActiveIsTrue(idx);
        ProductDto dto = ProductDto.fromEntity(entity);
        boolean isLike = false;
        if(phoneNumber != null) {
            Long uIdx = userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
            isLike = productLikeRepo.existsByIdxUserAndIdxProduct(uIdx, idx);
        }
        dto.setIsLike(isLike);
        return dto;
    }

    @Override
    public long countByIdxStore(Long sIdx) {
        return productRepo.countByIdxStoreAndIsActiveIsTrue(sIdx);
    }

    @Override
    public long countByIdxProductCategory(Long cIdx) {
        return productRepo.countByProductCategory_IdxAndIsActiveIsTrue(cIdx);
    }

}
