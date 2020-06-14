package com.mrporter.pomangam.test.data.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.DeliveryType;
import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import com.mrporter.pomangam.client.repositories.deliverysite.DeliverySiteJpaRepository;
import com.mrporter.pomangam.client.repositories.deliverysite.region.RegionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeliverySiteData {

    @Autowired
    DeliverySiteJpaRepository repository;

    @Transactional
    public void of(Long idx, String name, DeliveryType deliveryType, Long rIdx, String campus, String location) {
        DeliverySite entity = DeliverySite.builder()
                .idx(idx)
                .name(name)
                .deliveryType(deliveryType)
                .region(Region.builder().idx(rIdx).build())
                .campus(campus)
                .location(location)
                .build();
        repository.save(entity);
    }
}