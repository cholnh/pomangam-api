package com.mrporter.pomangam.client.services.store;

import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.domains.store.StoreSummaryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {
    List<StoreDto> findByIdxDeliverySite(Long dIdx, Pageable pageable);
    StoreDto findByIdx(Long idx);
    long count();
    List<StoreSummaryDto> findSummaries(Long dIdx, Pageable pageable);
}
