package com.mrporter.pomangam.orderEntry.orderTime.service;

import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTime;
import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeJpaRepository;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderTimeServiceImpl implements OrderTimeService {

    OrderTimeRepositoryImpl orderTimeRepository;
    OrderTimeJpaRepository orderTimeJpaRepository;

    @Override
    public OrderTime getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(Integer store_idx,
                                                                      Integer delivery_site_idx,
                                                                      String arrival_time) {
        return orderTimeJpaRepository.getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(store_idx,delivery_site_idx,arrival_time);
    }

    @Override
    public List<OrderTimeDto> getOrderTimesByDeliverySiteIdx(Integer delivery_site_idx) {
        return orderTimeRepository.getOrderTimesByDeliverySiteIdx(delivery_site_idx);
    }

    @Override
    public List<OrderTimeDto> getOrderTimesByDeliverySiteIdxAndArrivalTime(Integer delivery_site_idx) {
        return orderTimeRepository.getOrderTimesByDeliverySiteIdxAndArrivalTime(delivery_site_idx);
    }
}
