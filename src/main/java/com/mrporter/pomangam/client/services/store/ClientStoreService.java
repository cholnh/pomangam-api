package com.mrporter.pomangam.client.services.store;

import com.mrporter.pomangam.client.domains.store.SortType;
import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.domains.store.StoreQuantityOrderableDto;
import com.mrporter.pomangam.client.domains.store.StoreSummaryDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ClientStoreService {
    List<StoreDto> findByIdxDeliverySite(Long dIdx, Pageable pageable);
    StoreDto findByIdx(Long idx, String phoneNumber);
    long count();
    List<StoreSummaryDto> findOpeningStores(Long dIdx, Long oIdx, LocalDate oDate, Pageable pageable, SortType sortType);
    long countOpeningStores(Long dIdx, Long oIdx, LocalDate oDate);
    List<StoreQuantityOrderableDto> findQuantityOrderableByIdxes(Long dIdx, Long oIdx, LocalDate oDate, List<Long> sIdxes);
}
