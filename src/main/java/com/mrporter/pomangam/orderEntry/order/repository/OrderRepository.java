package com.mrporter.pomangam.orderEntry.order.repository;

import com.mrporter.pomangam.orderEntry.order.domain.OrderTimeSalesVolumeDto;

import java.util.List;

public interface OrderRepository {
    int getSalesVolumeByArrivalDateAndTimeAndStoreIdx(String arrival_date, String arrival_time, Integer store_idx);
    List<OrderTimeSalesVolumeDto> getSalesVolumeByArrivalDateAndStoreIdx(String arrival_date, Integer store_idx);
    int getBoxNo(Integer delivery_site_idx, String arrival_date_only, String arrival_time_only);
}
