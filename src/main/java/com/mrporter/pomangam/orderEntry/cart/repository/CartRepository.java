package com.mrporter.pomangam.orderEntry.cart.repository;

import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.time.ZoneId;
import java.util.Map;

public interface CartRepository {
    int countCart(@Param("cidx") Integer customer_idx);
    //ResponseEntity<?> getArrivalTimeMap(@Param("sidxes") String store_idxes, @Param("date") String arrival_date, @Param("didx") Integer delivery_site_idx);

    Map<Integer, Time> getCartWithArrivalTime(Integer cart_Idx);
    Map<Integer, Time> getCartWithArrivalTime(Integer cart_Idx, ZoneId zoneId);

}
