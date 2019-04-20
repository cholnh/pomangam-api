package com.mrporter.pomangam.orderEntry.order.repository;

import com.mrporter.pomangam.orderEntry.order.domain.OrderTimeSalesVolumeDto;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public int getSalesVolumeByArrivalDateAndTimeAndStoreIdx(String arrival_date, String arrival_time, Integer store_idx) {
        Query nativeQuery = em
                .createNativeQuery("SELECT SUM(oit.quantity) " +
                        "FROM order_tbl odr, item_for_order_tbl oit " +
                        "WHERE odr.arrival_date_only = ? " +
                        "AND oit.store_idx = ? " +
                        "AND odr.arrival_time_only = ? " +
                        "AND oit.order_idx = odr.idx")
                .setParameter(1, arrival_date)
                .setParameter(2, store_idx)
                .setParameter(3, arrival_time);

        int sv = 0;
        Object res = nativeQuery.getSingleResult();
        if(res != null) {
            sv = Integer.parseInt( res + "");
        }
        return sv;
    }

    @Override
    public List<OrderTimeSalesVolumeDto> getSalesVolumeByArrivalDateAndStoreIdx(String arrival_date, Integer store_idx) {
        Query nativeQuery = em
                .createNativeQuery("SELECT " +
                        "    ot.order_deadline, ot.state_pause, ot.arrival_time, t.sv " +
                        "FROM " +
                        "    ordertime_for_store_tbl ot " +
                        "        LEFT OUTER JOIN " +
                        "    (SELECT  " +
                        "        od.arrival_time_only AS arrival_time, SUM(oi.quantity) AS sv " +
                        "    FROM " +
                        "        item_for_order_tbl oi, order_tbl od " +
                        "    WHERE " +
                        "        oi.order_idx = od.idx " +
                        "            AND oi.store_idx = ? " +
                        "            AND od.arrival_date_only = ?) t " +
                        "ON ot.arrival_time = t.arrival_time " +
                        "WHERE " +
                        "    ot.store_idx = ?")
                .setParameter(1, store_idx)
                .setParameter(2, arrival_date)
                .setParameter(3, store_idx);

        List<OrderTimeSalesVolumeDto> svList = new JpaResultMapper().list(nativeQuery, OrderTimeSalesVolumeDto.class);

        return svList;
    }
}
