package com.mrporter.pomangam.storeEntry.store.service;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.storeEntry.store.domain.InquiryResultDto;
import com.mrporter.pomangam.storeEntry.store.domain.Store;
import com.mrporter.pomangam.storeEntry.store.domain.StoreSummaryDto;
import com.mrporter.pomangam.storeEntry.store.domain.StoreWithCategoryDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public interface StoreService {
    List<Store> getStoresByIdxes(List<Integer> idxes);

    List<InquiryResultDto> getInquiryResult(String arrival_date, Integer detail_for_delivery_site_idx);
    List<InquiryResultDto> getInquiryResult(LocalDateTime arrival_date, Integer detail_for_delivery_site_idx, ZoneId zoneId);
    List<StoreSummaryDto> findByType(Integer delivery_site_idx, Integer type, String orderBy, PageRequest pageRequest);
    StoreWithCategoryDto findWithCategory(Integer storeIdx);
}
