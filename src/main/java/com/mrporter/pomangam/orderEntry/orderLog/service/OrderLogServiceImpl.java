package com.mrporter.pomangam.orderEntry.orderLog.service;

import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.orderEntry.orderLog.domain.OrderLog;
import com.mrporter.pomangam.orderEntry.orderLog.repository.OrderLogJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderLogServiceImpl implements OrderLogService {

    OrderLogJpaRepository orderLogJpaRepository;

    @Override
    public void setState(String orderId, StateOrder stateOrder) {
        OrderLog log = orderLogJpaRepository.getByOrderId(orderId);
        log.setState_order(stateOrder.getCode());
        orderLogJpaRepository.save(log);
    }
}
