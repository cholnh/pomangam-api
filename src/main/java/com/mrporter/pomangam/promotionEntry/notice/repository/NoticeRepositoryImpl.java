package com.mrporter.pomangam.promotionEntry.notice.repository;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.notice.domain.NoticeResponseDto;
import lombok.AllArgsConstructor;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<NoticeResponseDto> getAll(Integer delivery_site_idx, PageRequest pageRequest) {
         Query nativeQuery = em
                .createNativeQuery(
                        "SELECT n.idx, n.title, n.begin_date, n.end_date, n.url " +
                        "FROM notice_tbl n " +
                        "WHERE n.idx IN (SELECT notice_idx FROM dsite_link_notice_tbl " + (delivery_site_idx != null ? "WHERE delivery_site_idx = ? " : "") + " ) " +
                        "AND n.begin_date <= NOW() AND (n.end_date IS NULL OR n.end_date > NOW()) " +
                        "AND n.state_active = 1 ");

        if(delivery_site_idx != null) {
            nativeQuery.setParameter(1, delivery_site_idx);
        }

        List notices = nativeQuery
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( NoticeResponseDto.class ) )
                .setFirstResult(pageRequest.getFirstIndex())
                .setMaxResults(pageRequest.getSize())
                .getResultList();
        return notices;
    }
}