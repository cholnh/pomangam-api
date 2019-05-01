package com.mrporter.pomangam.promotionEntry.event.repository;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.event.domain.EventResponseDto;
import lombok.AllArgsConstructor;
import org.hibernate.transform.Transformers;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class EventRepositoryImpl implements EventRepository {
    @PersistenceContext
    EntityManager em;

    public List<EventResponseDto> getInProgress(Integer delivery_site_idx) {
        Query nativeQuery = em.createNativeQuery(
                "SELECT evn.idx, evn.title, evn.begin_date, evn.end_date, evn.url, img.imgpath  " +
                        "FROM event_tbl evn LEFT OUTER JOIN imgpath_for_event_thumbnail_tbl img " +
                        "ON evn.idx = img.event_idx AND img.type = 0 " +
                        "WHERE evn.idx IN (SELECT event_idx FROM dsite_link_event_tbl WHERE delivery_site_idx = ?) " +
                        "AND evn.begin_date <= NOW() AND (evn.end_date IS NULL OR evn.end_date > NOW()) " +
                        "AND state_active = 1;"
        );
        nativeQuery.setParameter(1, delivery_site_idx);

        List<EventResponseDto> dtoList = new JpaResultMapper().list(nativeQuery, EventResponseDto.class);
        return dtoList;
    }

    public List<EventResponseDto> getFinished(Integer delivery_site_idx, PageRequest pageRequest) {
        List events = em
                .createNativeQuery("" +
                        "SELECT evn.idx, evn.title, evn.begin_date, evn.end_date, evn.url, img.imgpath  " +
                        "FROM event_tbl evn LEFT OUTER JOIN imgpath_for_event_thumbnail_tbl img " +
                        "ON evn.idx = img.event_idx AND img.type = 0 " +
                        "WHERE evn.idx IN (SELECT event_idx FROM dsite_link_event_tbl WHERE delivery_site_idx = ?) " +
                        "AND (evn.end_date IS NOT NULL AND evn.end_date < NOW()) " +
                        "AND state_active = 1 ")
                .setParameter(1, delivery_site_idx)
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( EventResponseDto.class ) )
                .setFirstResult(pageRequest.getFirstIndex())
                .setMaxResults(pageRequest.getSize())
                .getResultList();
        return events;
    }
}