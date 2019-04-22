package com.mrporter.pomangam.orderEntry.order.service;

import com.mrporter.pomangam.orderEntry.order.domain.OrderInfoDto;
import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

import java.util.List;

public interface OrderService {
    void setState(Integer order_idx, StateOrder stateOrder);
    void setImpUid(Integer order_idx, String imp_uid);

    List<OrderInfoDto> getCurrentOrderInfoByCustomerIdx(Integer customerIdx, PageRequest pageRequest);
    List<OrderInfoDto> getCurrentOrderInfoByGuestIdx(Integer guestIdx, PageRequest pageRequest);
    List<OrderInfoDto> getPastOrderInfoByCustomerIdx(Integer customerIdx, PageRequest pageRequest);
    List<OrderInfoDto> getPastOrderInfoByGuestIdx(Integer guestIdx, PageRequest pageRequest);
}
