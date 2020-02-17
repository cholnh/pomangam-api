package com.mrporter.pomangam.client.services.product.like;

import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.product.like.ProductLike;
import com.mrporter.pomangam.client.repositories.product.ProductJpaRepository;
import com.mrporter.pomangam.client.repositories.product.like.ProductLikeJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductLikeServiceImpl implements ProductLikeService {

    ProductLikeJpaRepository productLikeJpaRepository;
    ProductJpaRepository productJpaRepository;
    UserJpaRepository userJpaRepository;

    @Override
    public boolean toggle(String phoneNumber, Long pIdx) {
        Long uIdx = userJpaRepository.findIdxByPhoneNumber(phoneNumber);
        boolean like = productLikeJpaRepository.existsByIdxUser(uIdx);
        if(like) {
            cancelLike(uIdx, pIdx);
        } else {
            like(uIdx, pIdx);
        }
        return !like;
    }

    @Override
    public void like(String phoneNumber, Long pIdx) {
        Long uIdx = userJpaRepository.findIdxByPhoneNumber(phoneNumber);
        like(uIdx, pIdx);
    }

    @Override
    public void cancelLike(String phoneNumber, Long pIdx) {
        Long uIdx = userJpaRepository.findIdxByPhoneNumber(phoneNumber);
        cancelLike(uIdx, pIdx);
    }

    private void like(Long uIdx, Long pIdx) {
        saveLike(uIdx, pIdx);
        addCntLike(pIdx);
    }

    private void cancelLike(Long uIdx, Long pIdx) {
        deleteLike(uIdx, pIdx);
        subCntLike(pIdx);
    }

    private void saveLike(Long uIdx, Long pIdx) {
        ProductLike like = ProductLike.builder()
                .idxProduct(pIdx)
                .idxUser(uIdx)
                .build();
        productLikeJpaRepository.save(like);
    }

    private void deleteLike(Long uIdx, Long pIdx) {
        productLikeJpaRepository.deleteByIdxUserAndIdxProductQuery(uIdx, pIdx);
    }

    private void addCntLike(Long sIdx) {
        Optional<Product> optionalProduct = productJpaRepository.findById(sIdx);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.addCntLike();
            productJpaRepository.save(product);
        }
    }
    private void subCntLike(Long sIdx) {
        Optional<Product> optionalProduct = productJpaRepository.findById(sIdx);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.subCntLike();
            productJpaRepository.save(product);
        }
    }
}
