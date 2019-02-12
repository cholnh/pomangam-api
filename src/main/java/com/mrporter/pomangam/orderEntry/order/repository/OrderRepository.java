package com.mrporter.pomangam.orderEntry.order.repository;

public interface OrderRepository {
    int getSalesVolumeByArrivalDateAndTimeAndStoreIdx(String arrival_date, String arrival_time, Integer store_idx);
}
