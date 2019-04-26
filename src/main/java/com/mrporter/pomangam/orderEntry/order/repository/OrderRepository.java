package com.mrporter.pomangam.orderEntry.order.repository;

import com.mrporter.pomangam.orderEntry.order.domain.OrderDto;
import com.mrporter.pomangam.orderEntry.order.domain.OrderTimeSalesVolumeDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

import java.util.List;

public interface OrderRepository {
    int getSalesVolumeByArrivalDateAndTimeAndStoreIdx(String arrival_date, String arrival_time, Integer store_idx);
    List<OrderTimeSalesVolumeDto> getSalesVolumeByArrivalDateAndStoreIdx(String arrival_date, Integer store_idx);
    int getBoxNo(Integer delivery_site_idx, String arrival_date_only, String arrival_time_only);

    List<OrderDto> getTodayOrderByCustomerIdx(Integer customerIdx, PageRequest pageRequest);
    List<OrderDto> getTodayOrderByGuestIdx(Integer guestIdx, PageRequest pageRequest);
    List<OrderDto> getPastOrderInfoByCustomerId(Integer customerIdx, PageRequest pageRequest);
    List<OrderDto> getPastOrderInfoByGuestIdx(Integer guestIdx, PageRequest pageRequest);
}
