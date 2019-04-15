package com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository;

import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySiteDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailForDeliverySiteRepository {
    List<DetailForDeliverySiteDto> getDetailSitesByDeliverySiteIdxOrderBySequence(@Param("deliverySiteIdx") Integer delivery_site_idx);
    List<DetailForDeliverySiteDto> findByDeliverySiteIdxOrderBySequence(@Param("deliverySiteIdx") Integer delivery_site_idx);
}
