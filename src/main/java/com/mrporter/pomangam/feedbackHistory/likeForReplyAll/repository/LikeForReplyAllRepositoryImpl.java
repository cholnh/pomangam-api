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
        LikeForReplyAllKey key = new LikeForReplyAllKey(replyIdx, customerIdx);
        Optional<LikeForReplyAll> optional = likeForReplyAllJpaRepository.findById(key);
        LikeForReplyAll entity;
        if(optional.isPresent()) {
            entity = optional.get();
            if(entity.getType() == null) {
                entity.setType(type.byteValue());
                updateReplyAllLike(replyIdx, type, true);
            } else {
                if(entity.getType().intValue() != type.intValue()) {
                    entity.setType(type.byteValue());
                    updateReplyAllLike(replyIdx, type, false);
                } else {
                    entity.setType(null);
                    cancelReplyAllLike(replyIdx, type);
                }
            }
        } else {
            entity = LikeForReplyAll.builder()
                    .customer_idx(customerIdx)
                    .reply_all_idx(replyIdx)
                    .type(type.byteValue())
                    .build();
            updateReplyAllLike(replyIdx, type, true);
        }
        likeForReplyAllJpaRepository.save(entity);
    }

    private void cancelReplyAllLike(Integer replyIdx, Integer type) {
        String sql1;
        if (type.intValue() == 0) {
            sql1 = "UPDATE reply_for_comment_all_tbl SET cnt_like=cnt_like-1 WHERE idx=:replyIdx AND cnt_like>0";
        } else {
            sql1 = "UPDATE reply_for_comment_all_tbl SET cnt_unlike=cnt_unlike-1 WHERE idx=:replyIdx AND cnt_unlike>0";
        }
        em.createNativeQuery(sql1)
                .setParameter("replyIdx", replyIdx)
                .executeUpdate();
    }

    private void updateReplyAllLike(Integer replyIdx, Integer type, boolean isFirst) {
        String sql1, sql2;

        if(isFirst) {
            if (type.intValue() == 0) {
                sql1 = "UPDATE reply_for_comment_all_tbl SET cnt_like=cnt_like+1 WHERE idx=:replyIdx AND cnt_like>=0";
            } else {
                sql1 = "UPDATE reply_for_comment_all_tbl SET cnt_unlike=cnt_unlike+1 WHERE idx=:replyIdx AND cnt_unlike>=0";
            }
            em.createNativeQuery(sql1)
                    .setParameter("replyIdx", replyIdx)
                    .executeUpdate();
        } else {
            if (type.intValue() == 0) {
                sql1 = "UPDATE reply_for_comment_all_tbl SET cnt_like=cnt_like+1 WHERE idx=:replyIdx AND cnt_like>=0";
                sql2 = "UPDATE reply_for_comment_all_tbl SET cnt_unlike=cnt_unlike-1 WHERE idx=:replyIdx AND cnt_unlike>0";
            } else {
                sql1 = "UPDATE reply_for_comment_all_tbl SET cnt_unlike=cnt_unlike+1 WHERE idx=:replyIdx AND cnt_unlike>=0";
                sql2 = "UPDATE reply_for_comment_all_tbl SET cnt_like=cnt_like-1 WHERE idx=:replyIdx AND cnt_like>0";
            }
            em.createNativeQuery(sql1)
                    .setParameter("replyIdx", replyIdx)
                    .executeUpdate();
            em.createNativeQuery(sql2)
                    .setParameter("replyIdx", replyIdx)
                    .executeUpdate();
        }
    }
}