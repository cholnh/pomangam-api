package com.mrporter.pomangam.test.data.ordertimeMapper;

import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import com.mrporter.pomangam.client.domains.ordertime.OrderTimeMapper;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.repositories.ordertime.OrderTimeMapperJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderTimeMapperData {

    @Autowired
    OrderTimeMapperJpaRepository orderTimeMapperJpaRepository;

    @Transactional
    public void of(Long sIdx, Long ...oIdxes) {
        for(Long oIdx : oIdxes) {
            OrderTimeMapper mapper = OrderTimeMapper.builder()
                    .orderTime(OrderTime.builder()
                            .idx(oIdx)
                            .build())
                    .store(Store.builder()
                            .idx(sIdx)
                            .build())
                    .build();
            orderTimeMapperJpaRepository.save(mapper);
        }
    }
}