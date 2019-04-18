package com.mrporter.pomangam.storeEntry.store.repository;

import com.mrporter.pomangam.storeEntry.store.domain.StoreDto;

import java.util.List;

public interface StoreRepository {
    List<StoreDto> findByQuery(String query, Integer delivery_site_idx);
    //ResponseEntity<?> getInquiryResult(@Param("date") String arrival_date,
    //                                   @Param("deliverySiteIdx") Integer delivery_site_idx);
}
