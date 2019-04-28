package com.mrporter.pomangam.orderEntry.orderTime.repository;

import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;
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
        String sql = "SELECT str.idx, str.name, str.location, str.main_phone_number, str.description, str.cnt_like, str.cnt_comment, str.minimum_time, str.parallel_production, str.maximum_production, " +
                "odr.order_deadline as order_deadline, odr.state_pause as state_pause, img.imgpath, str.sequence " +
                "FROM ordertime_for_store_tbl odr, store_tbl str " +
                "LEFT OUTER JOIN imgpath_for_store_tbl img " +
                "ON img.store_idx = str.idx AND img.type = 0 " +
                "WHERE odr.delivery_site_idx = ? " +
                "AND odr.arrival_time = ? " +
                "AND odr.arrival_tomorrow = 0 " +
                "AND odr.store_idx = str.idx " +
                "ORDER BY str.sequence DESC , str.cnt_like";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);
        nativeQuery.setParameter(2, arrival_time);

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<InquiryResultDto> products = jpaResultMapper.list(nativeQuery, InquiryResultDto.class);
        return products;
    }

    /*
    @Override
    public InquiryResultDto getInquiryResult(Integer delivery_site_idx, String arrival_time, Integer store_idx) {
        String sql = "SELECT str.idx, str.name, str.location, str.main_phone_number, str.description, str.cnt_like, str.minimum_time, str.parallel_production, str.maximum_production, " +
                "odr.order_deadline as order_deadline, odr.state_pause as state_pause " +
                "FROM ordertime_for_store_tbl odr, store_tbl str " +
                "WHERE odr.delivery_site_idx = ? " +
                "AND odr.arrival_time = ? " +
                "AND odr.arrival_tomorrow = 0 " +
                "AND odr.store_idx = str.idx " +
                "AND str.idx = ?";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);
        nativeQuery.setParameter(2, arrival_time);
        nativeQuery.setParameter(3, store_idx);

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        InquiryResultDto products = jpaResultMapper.uniqueResult(nativeQuery, InquiryResultDto.class);
        return products;
    }
     */

    @Override
    public List<OrderTimeDto> getOrderTimesByDeliverySiteIdxAndArrivalTime(Integer delivery_site_idx) {
        String sql = "SELECT * " +
                "FROM ordertime_for_store_tbl " +
                "WHERE delivery_site_idx = ? " +
                "AND arrival_time > NOW() " +
                "GROUP BY arrival_time " +
                "ORDER BY sequence";
        return getOrderTimesWithSql(delivery_site_idx, sql);
    }

    @Override
    public List<OrderTimeDto> getOrderTimesByDeliverySiteIdx(Integer delivery_site_idx) {
        String sql = "SELECT * " +
                "FROM ordertime_for_store_tbl " +
                "WHERE delivery_site_idx = ? " +
                "GROUP BY arrival_time " +
                "ORDER BY sequence";
        return getOrderTimesWithSql(delivery_site_idx, sql);
    }

    private List<OrderTimeDto> getOrderTimesWithSql(Integer delivery_site_idx, String sql) {
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, delivery_site_idx);

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<OrderTimeDto> orderTimeDtoList = jpaResultMapper.list(nativeQuery, OrderTimeDto.class);
        return orderTimeDtoList;
    }
}
