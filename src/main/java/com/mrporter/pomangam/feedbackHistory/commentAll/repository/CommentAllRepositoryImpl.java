package com.mrporter.pomangam.feedbackHistory.commentAll.repository;

import com.mrporter.pomangam.common.util.sqlInjection.SqlInjection;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllDetailDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.StoreCategoryDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.hibernate.transform.Transformers;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class CommentAllRepositoryImpl implements CommentAllRepository {
    @PersistenceContext
    EntityManager em;

    SqlInjection sqlInjection;

    @Override
    public List<CommentAllDto> getAll(String orderBy, PageRequest pageRequest) {
        List comments = em
                .createNativeQuery(
                        "SELECT cmt.idx, st.name as store_name, cmt.title, cmt.cnt_like, cmt.cnt_unlike, cmt.cnt_view, cmt.register_date " +
                                "FROM comment_for_all_tbl cmt " +
                                "LEFT OUTER JOIN store_tbl st " +
                                "ON cmt.store_idx = st.idx " +
                                "WHERE cmt.state_active = 1 " +
                                (orderBy != null && !sqlInjection.isSQLInjection(orderBy) ? "ORDER BY " + orderBy :"ORDER BY cmt.cnt_like DESC ")
                )
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( CommentAllDto.class ) )
                .setFirstResult(pageRequest.getFirstIndex())
                .setMaxResults(pageRequest.getSize())
                .getResultList();
        return comments;
    }

    @Override
    public List<CommentAllDto> getBy(Integer deliverySiteIdx, Integer storeIdx, String orderBy, PageRequest pageRequest) {
        List comments = em
                .createNativeQuery(
                        "SELECT cmt.idx, st.name as store_name, cmt.title, cmt.cnt_like, cmt.cnt_unlike, cmt.cnt_view, cmt.register_date " +
                                "FROM comment_for_all_tbl cmt " +
                                "LEFT OUTER JOIN store_tbl st " +
                                "ON cmt.store_idx = st.idx " +
                                "WHERE cmt.state_active = 1 AND cmt.delivery_site_idx = :deliverySiteIdx " +
                                (storeIdx != null ? "AND cmt.store_idx = " + storeIdx : "") + " " +
                                (orderBy != null && !sqlInjection.isSQLInjection(orderBy) ? "ORDER BY " + orderBy :"ORDER BY cmt.cnt_like DESC ")
                )
                .setParameter("deliverySiteIdx", deliverySiteIdx)
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( CommentAllDto.class ) )
                .setFirstResult(pageRequest.getFirstIndex())
                .setMaxResults(pageRequest.getSize())
                .getResultList();
        return comments;
    }

    @Override
    public List<StoreCategoryDto> getStoreCategory(Integer deliverySiteIdx) {
        Query nativeQuery = em
                .createNativeQuery(
                        "SELECT st.idx as store_idx, st.name as store_name " +
                                "FROM comment_for_all_tbl cmt, store_tbl st " +
                                "WHERE cmt.store_idx = st.idx AND cmt.delivery_site_idx = ? " +
                                "group by cmt.store_idx")
                .setParameter(1, deliverySiteIdx);
        List<StoreCategoryDto> storeCategoryDtos = new JpaResultMapper().list(nativeQuery, StoreCategoryDto.class);
        return storeCategoryDtos;
    }

    @Override
    public CommentAllDetailDto getDetail(Integer commentIdx, Integer customerIdx) {
        Query nativeQuery = em
                .createNativeQuery(
                        "SELECT cmt.idx, st.name as store_name, cmt.title, cmt.contents, cmt.cnt_like, cmt.cnt_unlike, cmt.cnt_view, cmt.register_date, u.nickname, u.id as customer_id, cmt.state_anonymous, lk.type as likeType " +
                                "FROM comment_for_all_tbl cmt " +
                                "LEFT OUTER JOIN store_tbl st " +
                                "ON cmt.store_idx = st.idx " +
                                "LEFT OUTER JOIN user_tbl u " +
                                "ON cmt.customer_idx = u.idx AND (cmt.state_anonymous = 0 or u.idx = :customerIdx) " +
                                "LEFT OUTER JOIN like_for_comment_all_tbl lk " +
                                "ON cmt.idx = lk.comment_all_idx AND lk.customer_idx = :customerIdx " +
                                "WHERE cmt.state_active = 1 AND cmt.idx = :commentIdx ")
                .setParameter("commentIdx", commentIdx)
                .setParameter("customerIdx", customerIdx);
        List<CommentAllDetailDto> commentAllDetailDtos = new JpaResultMapper().list(nativeQuery, CommentAllDetailDto.class);
        if(commentAllDetailDtos.isEmpty()) {
            return null;
        } else {
            return commentAllDetailDtos.get(0);
        }
    }

    @Transactional
    @Override
    public void addViewCount(Integer commentIdx) {
        Query nativeQuery = em
                .createNativeQuery(
                        "UPDATE comment_for_all_tbl SET cnt_view = cnt_view + 1 WHERE idx = :commentIdx")
                .setParameter("commentIdx", commentIdx);
        nativeQuery.executeUpdate();
    }
}
