package com.mrporter.pomangam.orderEntry.orderTime.service;

import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTime;

public interface OrderTimeService {
    OrderTime getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(Integer store_idx,
                                                               Integer delivery_site_idx,
                                                               String arrival_time);
}
