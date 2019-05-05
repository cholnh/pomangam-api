package com.mrporter.pomangam.feedbackHistory.likeForCommentStore.repository;

import com.mrporter.pomangam.feedbackHistory.likeForCommentStore.domain.LikeForCommentStore;
import com.mrporter.pomangam.feedbackHistory.likeForCommentStore.domain.LikeForCommentStoreKey;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LikeForCommentStoreRepositoryImpl implements LikeForCommentStoreRepository {
    @PersistenceContext
    EntityManager em;

    LikeForCommentStoreJpaRepository likeForCommentStoreJpaRepository;

    @Override
    public Byte getType(Integer commentStoreIdx, Integer customerIdx) {
        Optional<LikeForCommentStore> optional = likeForCommentStoreJpaRepository.findById(new LikeForCommentStoreKey(commentStoreIdx, customerIdx));
        if(optional.isPresent()) {
            return optional.get().getType();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void like(Integer commentStoreIdx, Integer customerIdx) {
        updateLike(commentStoreIdx, customerIdx, 0);
    }

    @Transactional
    @Override
    public void unlike(Integer commentStoreIdx, Integer customerIdx) {
        updateLike(commentStoreIdx, customerIdx, 1);
    }

    private void updateLike(Integer commentStoreIdx, Integer customerIdx, Integer type) {
        Optional<LikeForCommentStore> optional = likeForCommentStoreJpaRepository.findById(new LikeForCommentStoreKey(commentStoreIdx, customerIdx));
        LikeForCommentStore entity;
        if(optional.isPresent()) {
            entity = optional.get();
            if(entity.getType().intValue() != type.intValue()) {
                entity.setType(type.byteValue());
                updateCommentStoreLike(commentStoreIdx, type);
            }
        } else {
            entity = LikeForCommentStore.builder()
                    .customer_idx(customerIdx)
                    .comment_store_idx(commentStoreIdx)
                    .type(type.byteValue())
                    .build();
            updateCommentStoreLike(commentStoreIdx, type);
        }
        likeForCommentStoreJpaRepository.save(entity);
    }

    private void updateCommentStoreLike(Integer commentStoreIdx, Integer type) {
        String sql;
        if (type.intValue() == 0) {
            sql = "UPDATE comment_for_store_tbl SET cnt_like=cnt_like+1 WHERE idx=:commentStoreIdx AND cnt_like>=0";
        } else {
            sql = "UPDATE comment_for_store_tbl SET cnt_like=cnt_like-1 WHERE idx=:commentStoreIdx AND cnt_like>0";
        }
        em.createNativeQuery(sql)
                .setParameter("commentStoreIdx", commentStoreIdx)
                .executeUpdate();
    }
}