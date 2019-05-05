package com.mrporter.pomangam.feedbackHistory.likeForStore.repository;

import com.mrporter.pomangam.feedbackHistory.likeForStore.domain.LikeForStore;
import com.mrporter.pomangam.feedbackHistory.likeForStore.domain.LikeForStoreKey;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LikeForStoreRepositoryImpl implements LikeForStoreRepository {
    @PersistenceContext
    EntityManager em;

    JpaResultMapper jpaResultMapper;

    LikeForStoreJpaRepository likeForStoreJpaRepository;

    @Override
    public Byte getType(Integer storeIdx, Integer customerIdx) {
        Optional<LikeForStore> optional = likeForStoreJpaRepository.findById(new LikeForStoreKey(storeIdx, customerIdx));
        if(optional.isPresent()) {
            return optional.get().getType();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void like(Integer storeIdx, Integer customerIdx) {
        updateLike(storeIdx, customerIdx, 0);
    }

    @Transactional
    @Override
    public void unlike(Integer storeIdx, Integer customerIdx) {
        updateLike(storeIdx, customerIdx, 1);
    }

    private void updateLike(Integer storeIdx, Integer customerIdx, Integer type) {
        Optional<LikeForStore> optional = likeForStoreJpaRepository.findById(new LikeForStoreKey(storeIdx, customerIdx));
        LikeForStore entity;
        if(optional.isPresent()) {
            entity = optional.get();
            if(entity.getType().intValue() != type.intValue()) {
                entity.setType(type.byteValue());
                updateStoreLike(storeIdx, type);
            }
        } else {
            entity = LikeForStore.builder()
                    .customer_idx(customerIdx)
                    .store_idx(storeIdx)
                    .type(type.byteValue())
                    .build();
            updateStoreLike(storeIdx, type);
        }
        likeForStoreJpaRepository.save(entity);
    }

    private void updateStoreLike(Integer storeIdx, Integer type) {
        String sql;
        if (type.intValue() == 0) {
            sql = "UPDATE store_tbl SET cnt_like=cnt_like+1 WHERE idx=:storeIdx AND cnt_like>=0";
        } else {
            sql = "UPDATE store_tbl SET cnt_like=cnt_like-1 WHERE idx=:storeIdx AND cnt_like>0";
        }
        em.createNativeQuery(sql)
                .setParameter("storeIdx", storeIdx)
                .executeUpdate();
    }
}