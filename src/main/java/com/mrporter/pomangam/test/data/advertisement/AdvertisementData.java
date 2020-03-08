package com.mrporter.pomangam.test.data.advertisement;

import com.mrporter.pomangam.client.domains.advertisement.Advertisement;
import com.mrporter.pomangam.client.domains.advertisement.AdvertisementMapper;
import com.mrporter.pomangam.client.domains.advertisement.AdvertisementType;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.repositories.advertisement.AdvertisementJpaRepository;
import com.mrporter.pomangam.client.repositories.advertisement.AdvertisementMapperJpaRepository;
import com.mrporter.pomangam.client.services._bases.ImagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdvertisementData {

    @Autowired
    AdvertisementJpaRepository advertisementJpaRepository;
    @Autowired
    AdvertisementMapperJpaRepository advertisementMapperJpaRepository;

    @Transactional
    public void of(Long idx, Long dIdx, String nextLocation, int sequence) {
        Advertisement advertisement = Advertisement.builder()
                .idx(idx)
                .advertisementType(AdvertisementType.MAIN)
                .imagePath(ImagePath.advertisements(dIdx, idx)+"1.png")
                .nextLocation(nextLocation)
                .sequence(sequence)
                .build();
        advertisementJpaRepository.save(advertisement);

        AdvertisementMapper advertisementMapper = AdvertisementMapper.builder()
                .advertisement(advertisement)
                .deliverySite(DeliverySite.builder().idx(dIdx).build())
                .build();
        advertisementMapperJpaRepository.save(advertisementMapper);
    }
}