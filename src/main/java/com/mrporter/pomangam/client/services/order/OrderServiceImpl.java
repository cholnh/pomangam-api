package com.mrporter.pomangam.client.services.order;

import com.mrporter.pomangam.client.domains.order.OrderResponseDto;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.order.log.OrderLog;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import com.mrporter.pomangam.client.repositories.order.OrderLogJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    OrderJpaRepository orderRepo;
    OrderLogJpaRepository orderLogRepo;

    public OrderResponseDto findByIdx(Long idx) {
        return OrderResponseDto.fromEntity(orderRepo.findByIdxAndIsActiveIsTrue(idx));
    }

    public void log(Long idxOrder, OrderType orderType) {
        OrderLog orderLog = OrderLog.builder()
                .idxOrder(idxOrder)
                .orderType(orderType)
                .registerDate(LocalDateTime.now())
                .build();
        orderLogRepo.save(orderLog);
    }
}
