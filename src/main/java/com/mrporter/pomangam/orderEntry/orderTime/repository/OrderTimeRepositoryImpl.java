package com.mrporter.pomangam.orderEntry.orderTime.repository;

import com.mrporter.pomangam.storeEntry.store.domain.InquiryResultDto;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderTimeRepositoryImpl implements OrderTimeRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<InquiryResultDto> getInquiryResult(Integer delivery_site_idx, String arrival_time) {
        String sql = "SELECT str.*, " +
                "odr.end_time as end_time, odr.state_pause as state_pause " +
                "FROM ordertime_for_store_tbl odr, store_tbl str " +
                "WHERE odr.delivery_site_idx = ? " +
                "AND odr.arrival_time = ? " +
                "AND odr.arrival_tomorrow = 0 " +
                "AND odr.store_idx = str.idx";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);
        nativeQuery.setParameter(2, arrival_time);

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<InquiryResultDto> products = jpaResultMapper.list(nativeQuery, InquiryResultDto.class);
        return products;
    }
}
