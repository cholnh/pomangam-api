package com.mrporter.pomangam.store.service;

import com.mrporter.pomangam.store.domain.Store;
import com.mrporter.pomangam.store.domain.StoreJoinOrderTimeDto;

import java.util.Date;
import java.util.List;

public interface StoreService {
    List<Store> getStoresByIdxes(List<Integer> idxes);

    List<StoreJoinOrderTimeDto> getStoresByArrivalTimeAndDeliverySite(Date arrival_date, Integer delivery_site_idx);
}
