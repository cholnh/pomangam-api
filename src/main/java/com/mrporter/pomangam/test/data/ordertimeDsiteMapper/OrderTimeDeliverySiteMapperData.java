package com.mrporter.pomangam.test.data.ordertimeDsiteMapper;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import com.mrporter.pomangam.client.domains.ordertime.OrderTimeDeliverySiteMapper;
import com.mrporter.pomangam.client.repositories.ordertime.OrderTimeDeliverySiteMapperJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderTimeDeliverySiteMapperData {

    @Autowired
    OrderTimeDeliverySiteMapperJpaRepository orderTimeDeliverySiteMapperJpaRepository;

    @Transactional
    public void of(Long dIdx, Long ...oIdxes) {
        for(Long oIdx : oIdxes) {
            OrderTimeDeliverySiteMapper mapper = OrderTimeDeliverySiteMapper.builder()
                    .orderTime(OrderTime.builder()
                            .idx(oIdx)
                            .build())
                    .deliverySite(DeliverySite.builder()
                            .idx(dIdx)
                            .build())
                    .build();
            orderTimeDeliverySiteMapperJpaRepository.save(mapper);
        }
    }
}