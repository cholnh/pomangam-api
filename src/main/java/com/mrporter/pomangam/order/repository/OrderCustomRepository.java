package com.mrporter.pomangam.order.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class OrderCustomRepository {

    @PersistenceContext
    EntityManager em;

    public int getCountByArrivalDateAndTimeAndStoreIdx(String arrival_date, String arrival_time, Integer store_idx) {
        String sql = "SELECT SUM(oit.quantity) " +
                "FROM order_tbl odr, order_item_tbl oit " +
                "WHERE odr.arrival_date_only = ? " +
                "AND oit.store_idx = ? " +
                "AND odr.arrival_time_only = ? " +
                "AND oit.order_idx = odr.idx";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, arrival_date);
        nativeQuery.setParameter(2, store_idx);
        nativeQuery.setParameter(3, arrival_time);

        int count = 0;
        Object res = nativeQuery.getSingleResult();
        if(res != null) {
            count = Integer.parseInt( res + "");
        }
        return count;
    }
}
