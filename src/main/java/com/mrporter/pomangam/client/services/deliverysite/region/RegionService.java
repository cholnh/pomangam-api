package com.mrporter.pomangam.client.services.deliverysite.region;

import com.mrporter.pomangam.client.domains.deliverysite.region.RegionDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RegionService {
    List<RegionDto> findAll(Pageable pageable);
    RegionDto findByIdx(Long idx);
    long count();
}
