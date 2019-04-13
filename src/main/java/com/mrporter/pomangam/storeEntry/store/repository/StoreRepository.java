package com.mrporter.pomangam.storeEntry.store.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

public interface StoreRepository {
    ResponseEntity<?> getInquiryResult(@Param("date") String arrival_date,
                                       @Param("deliverySiteIdx") Integer delivery_site_idx);
}
