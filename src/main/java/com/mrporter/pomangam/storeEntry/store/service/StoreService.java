package com.mrporter.pomangam.storeEntry.store.service;

import com.mrporter.pomangam.storeEntry.store.domain.InquiryResultDto;
import com.mrporter.pomangam.storeEntry.store.domain.Store;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public interface StoreService {
    List<Store> getStoresByIdxes(List<Integer> idxes);

    List<InquiryResultDto> getInquiryResult(String arrival_date, Integer detail_for_delivery_site_idx) ;

    List<InquiryResultDto> getInquiryResult(LocalDateTime arrival_date, Integer detail_for_delivery_site_idx, ZoneId zoneId);
}
