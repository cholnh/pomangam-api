package com.mrporter.pomangam.feedbackHistory.commentStore.repository;

import com.mrporter.pomangam.common.util.sqlInjection.SqlInjection;
import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreViewDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@AllArgsConstructor
public class CommentStoreRepositoryImpl implements CommentStoreRepository {
    @PersistenceContext
    EntityManager em;

    SqlInjection sqlInjection;

    @Override
    public double getAvgStar(Integer storeIdx) {
        Object obj = em
                .createNativeQuery("SELECT ROUND(AVG(cnt_star), 1) FROM comment_for_store_tbl WHERE store_idx = :storeIdx AND state_active = 1")
                .setParameter("storeIdx", storeIdx)
                .getSingleResult();
        if(obj == null) {
            return 0;
        }
        Double result = (Double) obj;
        return result.doubleValue();
    }

    @Override
    public List<CommentStoreViewDto> findByStoreIdx(Integer storeIdx, String orderBy, PageRequest pageRequest) {
        List comments = em
                .createNativeQuery(
                        "SELECT cmt.idx, cmt.store_idx, u.nickname AS customer_nickname , cmt.cnt_star, cmt.cnt_like, cmt.contents, cmt.register_date, cmt.state_anonymous " +
                                "FROM comment_for_store_tbl cmt, user_tbl u " +
                                "WHERE cmt.customer_idx = u.idx " +
                                "AND cmt.store_idx = :storeIdx " +
                                "AND cmt.state_active = 1 " +
                                (orderBy != null && !sqlInjection.isSQLInjection(orderBy) ? "ORDER BY " + orderBy :"ORDER BY cmt.cnt_like DESC ")
                )
                .setParameter("storeIdx", storeIdx)
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( CommentStoreViewDto.class ) )
                .setFirstResult(pageRequest.getFirstIndex())
                .setMaxResults(pageRequest.getSize())
                .getResultList();
        return comments;
    }
}
