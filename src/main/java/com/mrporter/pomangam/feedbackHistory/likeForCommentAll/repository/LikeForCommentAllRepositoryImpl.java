package com.mrporter.pomangam.feedbackHistory.likeForCommentAll.repository;

import com.mrporter.pomangam.feedbackHistory.likeForCommentAll.domain.LikeForCommentAll;
import com.mrporter.pomangam.feedbackHistory.likeForCommentAll.domain.LikeForCommentAllKey;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LikeForCommentAllRepositoryImpl implements LikeForCommentAllRepository {
    @PersistenceContext
    EntityManager em;

    LikeForCommentAllJpaRepository likeForCommentAllJpaRepository;

    @Override
    public Byte getType(Integer commentAllIdx, Integer customerIdx) {
        Optional<LikeForCommentAll> optional = likeForCommentAllJpaRepository.findById(new LikeForCommentAllKey(commentAllIdx, customerIdx));
        if(optional.isPresent()) {
            return optional.get().getType();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void like(Integer commentAllIdx, Integer customerIdx) {
        updateLike(commentAllIdx, customerIdx, 0);
    }

    @Transactional
    @Override
    public void unlike(Integer commentAllIdx, Integer customerIdx) {
        updateLike(commentAllIdx, customerIdx, 1);
    }

    private void updateLike(Integer commentAllIdx, Integer customerIdx, Integer type) {
        LikeForCommentAllKey key = new LikeForCommentAllKey(commentAllIdx, customerIdx);
        Optional<LikeForCommentAll> optional = likeForCommentAllJpaRepository.findById(key);
        LikeForCommentAll entity;
        if(optional.isPresent()) {
            entity = optional.get();
            if(entity.getType() == null) {
                entity.setType(type.byteValue());
                updateCommentAllLike(commentAllIdx, type, true);
            } else {
                if(entity.getType().intValue() != type.intValue()) {
                    entity.setType(type.byteValue());
                    updateCommentAllLike(commentAllIdx, type, false);
                } else {
                    entity.setType(null);
                    cancelCommentAllLike(commentAllIdx, type);
                }
            }
        } else {
            entity = LikeForCommentAll.builder()
                    .customer_idx(customerIdx)
                    .comment_all_idx(commentAllIdx)
                    .type(type.byteValue())
                    .build();
            updateCommentAllLike(commentAllIdx, type, true);
        }
        likeForCommentAllJpaRepository.save(entity);
    }

    private void cancelCommentAllLike(Integer commentAllIdx, Integer type) {
        String sql1;
        if (type.intValue() == 0) {
            sql1 = "UPDATE comment_for_all_tbl SET cnt_like=cnt_like-1 WHERE idx=:commentAllIdx AND cnt_like>0";
        } else {
            sql1 = "UPDATE comment_for_all_tbl SET cnt_unlike=cnt_unlike-1 WHERE idx=:commentAllIdx AND cnt_unlike>0";
        }
        em.createNativeQuery(sql1)
                .setParameter("commentAllIdx", commentAllIdx)
                .executeUpdate();
    }

    private void updateCommentAllLike(Integer commentAllIdx, Integer type, boolean isFirst) {
        String sql1, sql2;

        if (isFirst) {
            if (type.intValue() == 0) {
                sql1 = "UPDATE comment_for_all_tbl SET cnt_like=cnt_like+1 WHERE idx=:commentAllIdx AND cnt_like>=0";
            } else {
                sql1 = "UPDATE comment_for_all_tbl SET cnt_unlike=cnt_unlike+1 WHERE idx=:commentAllIdx AND cnt_unlike>=0";
            }
            em.createNativeQuery(sql1)
                    .setParameter("commentAllIdx", commentAllIdx)
                    .executeUpdate();
        } else {
            if (type.intValue() == 0) {
                sql1 = "UPDATE comment_for_all_tbl SET cnt_like=cnt_like+1 WHERE idx=:commentAllIdx AND cnt_like>=0";
                sql2 = "UPDATE comment_for_all_tbl SET cnt_unlike=cnt_unlike-1 WHERE idx=:commentAllIdx AND cnt_unlike>0";
            } else {
                sql1 = "UPDATE comment_for_all_tbl SET cnt_unlike=cnt_unlike+1 WHERE idx=:commentAllIdx AND cnt_unlike>=0";
                sql2 = "UPDATE comment_for_all_tbl SET cnt_like=cnt_like-1 WHERE idx=:commentAllIdx AND cnt_like>0";
            }
            em.createNativeQuery(sql1)
                    .setParameter("commentAllIdx", commentAllIdx)
                    .executeUpdate();
            em.createNativeQuery(sql2)
                    .setParameter("commentAllIdx", commentAllIdx)
                    .executeUpdate();
        }
    }
}