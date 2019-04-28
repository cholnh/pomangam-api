package com.mrporter.pomangam.orderEntry.orderTime.service;

import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTime;
import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderTimeService {
    OrderTime getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(Integer store_idx,
                                                               Integer delivery_site_idx,
                                                               String arrival_time);
    List<OrderTimeDto> getOrderTimesByDeliverySiteIdx(Integer delivery_site_idx);
    List<OrderTimeDto> getOrderTimesByDeliverySiteIdxAndArrivalTime(Integer delivery_site_idx);
    LocalDateTime getMinimumArrivalTime(Integer store_idx, int quantity);
}
