package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.repository;

import com.mrporter.pomangam.common.util.sqlInjection.SqlInjection;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAllDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@AllArgsConstructor
public class ReplyForCommentAllRepositoryImpl implements ReplyForCommentAllRepository {
    @PersistenceContext
    EntityManager em;

    SqlInjection sqlInjection;

    @Override
    public List<ReplyForCommentAllDto> getBy(Integer commentIdx, Integer customerIdx, PageRequest pageRequest) {
        List replies = em
                .createNativeQuery(
                        "SELECT re.idx as replyIdx, u.nickname, u.id as customer_id, re.state_anonymous, re.owner_idx, re.register_date, re.contents, lk.type as likeType, re.cnt_like, re.cnt_unlike  " +
                                "FROM reply_for_comment_all_tbl re " +
                                "LEFT OUTER JOIN user_tbl u " +
                                "ON re.customer_idx = u.idx AND (re.state_anonymous = 0 or u.idx = :customerIdx) " +
                                "LEFT OUTER JOIN like_for_reply_all_tbl lk " +
                                "ON re.idx = lk.reply_all_idx AND lk.customer_idx = :customerIdx " +
                                "WHERE re.state_active = 1 AND re.comment_all_idx = :commentIdx "
                )
                .setParameter("commentIdx", commentIdx)
                .setParameter("customerIdx", customerIdx)
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( ReplyForCommentAllDto.class ) )
                .setFirstResult(pageRequest.getFirstIndex())
                .setMaxResults(pageRequest.getSize())
                .getResultList();
        return replies;
    }
}
