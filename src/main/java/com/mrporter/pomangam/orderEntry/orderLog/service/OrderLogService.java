package com.mrporter.pomangam.orderEntry.orderLog.service;

import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;

public interface OrderLogService {
    void setState(String orderId, StateOrder stateOrder);
}
