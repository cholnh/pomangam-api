package com.mrporter.pomangam.storeEntry.store.repository;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.storeEntry.store.domain.StoreDto;
import com.mrporter.pomangam.storeEntry.store.domain.StoreSummaryDto;

import java.util.List;

public interface StoreRepository {
    List<StoreDto> findByQuery(String query, Integer delivery_site_idx);
    List<StoreSummaryDto> findByType(Integer delivery_site_idx, Integer type, PageRequest pageRequest);
}
