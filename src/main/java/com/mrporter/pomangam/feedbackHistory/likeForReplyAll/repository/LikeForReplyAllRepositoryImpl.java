package com.mrporter.pomangam.feedbackHistory.likeForReplyAll.repository;

import com.mrporter.pomangam.feedbackHistory.likeForReplyAll.domain.LikeForReplyAll;
import com.mrporter.pomangam.feedbackHistory.likeForReplyAll.domain.LikeForReplyAllKey;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LikeForReplyAllRepositoryImpl implements LikeForReplyAllRepository {
    @PersistenceContext
    EntityManager em;

    LikeForReplyAllJpaRepository likeForReplyAllJpaRepository;

    @Override
    public Byte getType(Integer replyIdx, Integer customerIdx) {
        Optional<LikeForReplyAll> optional = likeForReplyAllJpaRepository.findById(new LikeForReplyAllKey(replyIdx, customerIdx));
        if(optional.isPresent()) {
            return optional.get().getType();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void like(Integer replyIdx, Integer customerIdx) {
        updateLike(replyIdx, customerIdx, 0);
    }

    @Transactional
    @Override
    public void unlike(Integer replyIdx, Integer customerIdx) {
        updateLike(replyIdx, customerIdx, 1);
    }

    private void updateLike(Integer replyIdx, Integer customerIdx, Integer type) {
        Optional<LikeForReplyAll> optional = likeForReplyAllJpaRepository.findById(new LikeForReplyAllKey(replyIdx, customerIdx));
        LikeForReplyAll entity;
        if(optional.isPresent()) {
            entity = optional.get();
            if(entity.getType().intValue() != type.intValue()) {
                entity.setType(type.byteValue());
                updateReplyAllLike(replyIdx, type);
            }
        } else {
            entity = LikeForReplyAll.builder()
                    .customer_idx(customerIdx)
                    .reply_all_idx(replyIdx)
                    .type(type.byteValue())
                    .build();
            updateReplyAllLike(replyIdx, type);
        }
        likeForReplyAllJpaRepository.save(entity);
    }

    private void updateReplyAllLike(Integer replyIdx, Integer type) {
        String sql;
        if (type.intValue() == 0) {
            sql = "UPDATE like_for_reply_all_tbl SET cnt_like=cnt_like+1 WHERE idx=:replyIdx AND cnt_like>=0";
        } else {
            sql = "UPDATE like_for_reply_all_tbl SET cnt_like=cnt_like-1 WHERE idx=:replyIdx AND cnt_like>0";
        }
        em.createNativeQuery(sql)
                .setParameter("replyIdx", replyIdx)
                .executeUpdate();
    }
}