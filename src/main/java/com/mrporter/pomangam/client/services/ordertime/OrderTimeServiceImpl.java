package com.mrporter.pomangam.client.services.ordertime;

import com.mrporter.pomangam.client.domains.ordertime.OrderTimeDto;
import com.mrporter.pomangam.client.repositories.ordertime.OrderTimeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderTimeServiceImpl implements OrderTimeService {

    private OrderTimeJpaRepository orderTimeRepo;

    public List<OrderTimeDto> findByIdxDeliverySite(Long dIdx) {
        return OrderTimeDto.fromEntities(orderTimeRepo.findByIdxDeliverySiteAndIsActiveIsTrue(dIdx));
    }
}
