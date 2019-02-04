package com.mrporter.pomangam.store.service;

import com.mrporter.pomangam.store.domain.Store;
import com.mrporter.pomangam.store.domain.StoreJoinOrderTimeDto;

import java.util.Date;
import java.util.List;

public interface StoreService {
    List<Store> getStoresByIdxes(List<Long> idxes);

    List<StoreJoinOrderTimeDto> getStoresByArrivalTimeAndDetailDeliverySite(Date arrival_date, Long delivery_site_idx, Long detail_delivery_site_idx);

    //List<OrderTime> getOrderTimeByArrivalTime(Long delivery_site_idx, String arrival_time);
}
