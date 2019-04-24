package com.mrporter.pomangam.orderEntry.order.service;

import com.mrporter.pomangam.orderEntry.order.domain.Order;
import com.mrporter.pomangam.orderEntry.order.domain.OrderInfoDto;
import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.orderEntry.order.repository.OrderJpaRepository;
import com.mrporter.pomangam.orderEntry.order.repository.OrderRepositoryImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    OrderJpaRepository orderJpaRepository;
    OrderRepositoryImpl orderRepository;

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

    @Override
    public List<OrderInfoDto> getCurrentOrderInfoByCustomerId(String customerId, PageRequest pageRequest) {
        List<OrderInfoDto> orderInfoDtoList = new ArrayList<>();



        OrderInfoDto dto = new OrderInfoDto();
        orderInfoDtoList.add(dto);

        return orderInfoDtoList;
    }

    @Override
    public List<OrderInfoDto> getCurrentOrderInfoByGuestIdx(Integer guestIdx, PageRequest pageRequest) {
        List<OrderInfoDto> orderInfoDtoList = new ArrayList<>();

        // Todo ...

        return orderInfoDtoList;
    }

    @Override
    public List<OrderInfoDto> getPastOrderInfoByCustomerId(String customerId, PageRequest pageRequest) {
        List<OrderInfoDto> orderInfoDtoList = new ArrayList<>();

        // Todo ...

        return orderInfoDtoList;
    }

    @Override
    public List<OrderInfoDto> getPastOrderInfoByGuestIdx(Integer guestIdx, PageRequest pageRequest) {
        List<OrderInfoDto> orderInfoDtoList = new ArrayList<>();

        // Todo ...

        return orderInfoDtoList;
    }
}
