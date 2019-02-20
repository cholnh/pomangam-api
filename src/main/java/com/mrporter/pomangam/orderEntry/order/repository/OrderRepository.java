package com.mrporter.pomangam.orderEntry.order.repository;

import com.mrporter.pomangam.orderEntry.order.domain.SalesVolumeDto;

import java.util.List;

public interface OrderRepository {
    int getSalesVolumeByArrivalDateAndTimeAndStoreIdx(String arrival_date, String arrival_time, Integer store_idx);
    List<SalesVolumeDto> getSalesVolumeByArrivalDateAndStoreIdx(String arrival_date, Integer store_idx);
}
