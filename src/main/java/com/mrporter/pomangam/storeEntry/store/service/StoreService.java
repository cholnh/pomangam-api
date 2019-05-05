package com.mrporter.pomangam.storeEntry.store.service;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.storeEntry.store.domain.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public interface StoreService {
    List<Store> getStoresByIdxes(List<Integer> idxes);

    List<InquiryResultDto> getInquiryResult(String arrival_date, Integer detail_for_delivery_site_idx);
    List<InquiryResultDto> getInquiryResult(LocalDateTime arrival_date, Integer detail_for_delivery_site_idx, ZoneId zoneId);
    List<StoreSummaryDto> findByType(Integer delivery_site_idx, Integer type, String orderBy, PageRequest pageRequest);
    StoreWithCategoryDto findWithCategory(Integer storeIdx, String customerId);

    void like(Integer storeIdx, String customerId);
    void unlike(Integer storeIdx, String customerId);

    StoreInfoDto getInfo(Integer storeIdx);
}
