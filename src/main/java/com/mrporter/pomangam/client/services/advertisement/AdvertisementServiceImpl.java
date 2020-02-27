package com.mrporter.pomangam.client.services.advertisement;

import com.mrporter.pomangam.client.domains.advertisement.Advertisement;
import com.mrporter.pomangam.client.domains.advertisement.AdvertisementDto;
import com.mrporter.pomangam.client.repositories.advertisement.AdvertisementJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementJpaRepository advertisementRepo;

    @Override
    public List<AdvertisementDto> findByIdxDeliverySite(Long dIdx, Pageable pageable) {
        List<Advertisement> advertisements = advertisementRepo.findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(dIdx, pageable).getContent();
        return AdvertisementDto.fromEntities(advertisements);
    }
}
