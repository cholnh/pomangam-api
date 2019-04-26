package com.mrporter.pomangam.deliveryEntry.deliverySite.repository;

import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteDto;
import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteWithCountDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliverySiteRepository {
    DeliverySiteDto getByDeliverySiteIdx(@Param("deliverySiteIdx") Integer deliverySiteIdx);
    List<DeliverySiteWithCountDto> findByQuery(@Param("query") String query);
    List<DeliverySiteDto> findByRegionCategoryIdx(@Param("regionCategoryIdx") Integer regionCategoryIdx);
    List<DeliverySiteWithCountDto> findByConsonantQuery(String query);
}
