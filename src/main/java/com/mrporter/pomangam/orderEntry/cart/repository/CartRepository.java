package com.mrporter.pomangam.orderEntry.cart.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

public interface CartRepository {
    ResponseEntity<?> countCart(@Param("cidx") Integer customer_idx);
    ResponseEntity<?> getArrivalTimeMap(@Param("sidxes") String store_idxes, @Param("date") String arrival_date, @Param("didx") Integer delivery_site_idx);
}
