package com.mrporter.pomangam.orderEntry.orderTime.service;

import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTime;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderTimeServiceImpl implements OrderTimeService {

    OrderTimeJpaRepository orderTimeJpaRepository;

    @Override
    public OrderTime getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(Integer store_idx,
                                                                      Integer delivery_site_idx,
                                                                      String arrival_time) {
        return orderTimeJpaRepository.getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(store_idx,delivery_site_idx,arrival_time);
    }
}
