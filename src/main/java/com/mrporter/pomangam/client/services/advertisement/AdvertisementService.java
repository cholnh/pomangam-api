package com.mrporter.pomangam.client.services.advertisement;

import com.mrporter.pomangam.client.domains.advertisement.AdvertisementDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdvertisementService {
    List<AdvertisementDto> findByIdxDeliverySite(Long dIdx, Pageable pageable);
}
