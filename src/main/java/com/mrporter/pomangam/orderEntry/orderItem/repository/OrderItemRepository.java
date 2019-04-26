package com.mrporter.pomangam.orderEntry.orderItem.repository;

import com.mrporter.pomangam.orderEntry.orderItem.domain.OrderInfoItemDto;

import java.util.List;

public interface OrderItemRepository {
    List<OrderInfoItemDto> findOrderInfoItem(Integer orderIdx);
}
