package com.mrporter.pomangam.test.data.region;

import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import com.mrporter.pomangam.client.repositories.deliverysite.region.RegionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RegionData {

    @Autowired
    RegionJpaRepository regionJpaRepository;

    @Transactional
    public void of(Long idx, String name) {
        Region region = Region.builder()
                .idx(idx)
                .name(name)
                .build();
        regionJpaRepository.save(region);
    }
}