package com.mrporter.pomangam.deliveryEntry.deliverySite.repository;

import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteDto;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

public interface DeliverySiteRepository {
    DeliverySiteDto getByDeliverySiteIdx(@Param("deliverySiteIdx") Integer deliverySiteIdx);

    ResponseEntity<?> findByQuery(@Param("query") String query);
    ResponseEntity<?> findByRegionCategoryIdx(@Param("regionCategoryIdx") Integer regionCategoryIdx);
}
