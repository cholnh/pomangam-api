package com.mrporter.pomangam.orderEntry.cart.repository;

import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Map;

public interface CartRepository {
    int countCart(@Param("customerIdx") Integer customer_idx);

    //ResponseEntity<?> getArrivalTimeMap(@Param("sidxes") String store_idxes, @Param("date") String arrival_date, @Param("didx") Integer delivery_site_idx);
    Map<Integer, Time> getCartWithArrivalTimeByCustomerIdx(@Param("customerIdx") Integer customer_idx);
    Map<Integer, Time> getCartWithArrivalTimeByCartIdx(@Param("cartIdx") Integer cart_Idx);
    //Map<Integer, Time> getCartWithArrivalTime(@Param("cidx") Integer cart_Idx, @Param("zoneid") ZoneId zoneId);

}
