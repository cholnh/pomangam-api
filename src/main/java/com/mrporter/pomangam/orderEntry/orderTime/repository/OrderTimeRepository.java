package com.mrporter.pomangam.orderEntry.orderTime.repository;

import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;
import com.mrporter.pomangam.storeEntry.store.domain.InquiryResultDto;

import java.util.List;

public interface OrderTimeRepository {
    List<InquiryResultDto> getInquiryResult(Integer delivery_site_idx, String arrival_time);
    InquiryResultDto getInquiryResult(Integer delivery_site_idx, String arrival_time, Integer store_idx);
    List<OrderTimeDto> getOrderTimesByDeliverySiteIdx(Integer delivery_site_idx);
    List<OrderTimeDto> getOrderTimesByDeliverySiteIdxAndArrivalTime(Integer delivery_site_idx);
}
