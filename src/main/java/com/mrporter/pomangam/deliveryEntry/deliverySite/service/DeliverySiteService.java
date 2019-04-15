package com.mrporter.pomangam.deliveryEntry.deliverySite.service;

import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliverySiteService {
    DeliverySiteDto getByDeliverySiteIdx(@Param("deliverySiteIdx") Integer deliverySiteIdx);
    List<DeliverySiteDto> findByQuery(@Param("query") String query);
    List<DeliverySiteDto> findByRegionCategoryIdx(@Param("regionCategoryIdx") Integer regionCategoryIdx);
}
