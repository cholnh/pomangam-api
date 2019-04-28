package com.mrporter.pomangam.feedbackHistory.commentStore.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Repository
@AllArgsConstructor
public class CommentStoreRepositoryImpl implements CommentStoreRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public double getAvgStar(Integer storeIdx) {
        Object obj = em
                .createNativeQuery("SELECT AVG(cnt_star) FROM comment_for_store_tbl WHERE store_idx = :storeIdx AND state_active = 1")
                .setParameter("storeIdx", storeIdx)
                .getSingleResult();
        if(obj == null) {
            return 0;
        }
        BigDecimal result = (BigDecimal) obj;
        return result.doubleValue();
    }
}
