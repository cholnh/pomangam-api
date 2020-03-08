package com.mrporter.pomangam.test.data.detailsite;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.repositories.deliverysite.detail.DeliveryDetailSiteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Component
public class DeliveryDetailSiteData {

    @Autowired
    DeliveryDetailSiteJpaRepository repository;

    @Transactional
    public void of(Long idx, Long dIdx, String name, String location, String abbreviation, int sequence, double latitude, double longitude, int addMinute) {
        DeliveryDetailSite entity = DeliveryDetailSite.builder()
                .idx(idx)
                .name(name)
                .location(location)
                .abbreviation(abbreviation)
                .sequence(sequence)
                .deliverySite(DeliverySite.builder().idx(dIdx).build())
                .latitude(latitude)
                .longitude(longitude)
                .additionalTime(LocalTime.of(0,addMinute,0))
                .build();
        repository.save(entity);
    }
}