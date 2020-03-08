package com.mrporter.pomangam.test.data.ordertime;

import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import com.mrporter.pomangam.client.repositories.ordertime.OrderTimeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Component
public class OrderTimeData {

    @Autowired
    OrderTimeJpaRepository orderTimeJpaRepository;

    @Transactional
    public void of(Long idx, LocalTime orderEnd, LocalTime pickUp, LocalTime arrival) {
        OrderTime orderTime = OrderTime.builder()
                .idx(idx)
                .orderEndTime(orderEnd)
                .pickUpTime(pickUp)
                .arrivalTime(arrival)
                .build();
        orderTimeJpaRepository.save(orderTime);
    }
}