package com.mrporter.pomangam.orderEntry.order.service;

import com.mrporter.pomangam.orderEntry.order.domain.Order;
import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.orderEntry.order.repository.OrderJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    OrderJpaRepository orderJpaRepository;

    @Override
    public void setState(Integer order_idx, StateOrder stateOrder) {
        Order order = orderJpaRepository.getOne(order_idx);
        order.setState_order(stateOrder.getCode());
        orderJpaRepository.save(order);
    }

    @Override
    public void setImpUid(Integer order_idx, String imp_uid) {
        Order order = orderJpaRepository.getOne(order_idx);
        order.setImp_uid(imp_uid);
        orderJpaRepository.save(order);
    }
}
