package com.mrporter.pomangam.orderEntry.order.service;

import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;

public interface OrderService {
    void setState(Integer order_idx, StateOrder stateOrder);
    void setImpUid(Integer order_idx, String imp_uid);
}
