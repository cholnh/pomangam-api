package com.mrporter.pomangam.feedbackHistory.likeForProduct.repository;

import com.mrporter.pomangam.feedbackHistory.likeForProduct.domain.LikeForProduct;
import com.mrporter.pomangam.feedbackHistory.likeForProduct.domain.LikeForProductKey;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LikeForProductRepositoryImpl implements LikeForProductRepository {
    @PersistenceContext
    EntityManager em;

    JpaResultMapper jpaResultMapper;

    LikeForProductJpaRepository likeForProductJpaRepository;

    @Override
    public Byte getType(Integer productIdx, Integer customerIdx) {
        Optional<LikeForProduct> optional = likeForProductJpaRepository.findById(new LikeForProductKey(productIdx, customerIdx));
        if(optional.isPresent()) {
            return optional.get().getType();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void like(Integer productIdx, Integer customerIdx) {
        updateLike(productIdx, customerIdx, 0);
    }

    @Transactional
    @Override
    public void unlike(Integer productIdx, Integer customerIdx) {
        updateLike(productIdx, customerIdx, 1);
    }

    private void updateLike(Integer productIdx, Integer customerIdx, Integer type) {
        Optional<LikeForProduct> optional = likeForProductJpaRepository.findById(new LikeForProductKey(productIdx, customerIdx));
        LikeForProduct entity;
        if(optional.isPresent()) {
            entity = optional.get();
            if(entity.getType().intValue() != type.intValue()) {
                entity.setType(type.byteValue());
                updateProductLike(productIdx, type);
            }
        } else {
            entity = LikeForProduct.builder()
                    .customer_idx(customerIdx)
                    .product_idx(productIdx)
                    .type(type.byteValue())
                    .build();
            updateProductLike(productIdx, type);
        }
        likeForProductJpaRepository.save(entity);
    }

    private void updateProductLike(Integer productIdx, Integer type) {
        String sql;
        if (type.intValue() == 0) {
            sql = "UPDATE product_tbl SET cnt_like=cnt_like+1 WHERE idx=:productIdx AND cnt_like>=0";
        } else {
            sql = "UPDATE product_tbl SET cnt_like=cnt_like-1 WHERE idx=:productIdx AND cnt_like>0";
        }
        em.createNativeQuery(sql)
                .setParameter("productIdx", productIdx)
                .executeUpdate();
    }
}