package com.mrporter.pomangam.advertiseEntry.advertiseForMain.service;

import com.mrporter.pomangam.advertiseEntry.advertiseForMain.domain.AdvertiseForMainDto;
import com.mrporter.pomangam.advertiseEntry.advertiseForMain.repository.AdvertiseForMainRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdvertiseForMainServiceImpl implements AdvertiseForMainService {
    AdvertiseForMainRepositoryImpl advertiseForMainRepository;


    @Cacheable(value="getAdvertiseMainCache", key="#delivery_site_idx")
    @Override
    public List<AdvertiseForMainDto> getAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx) {
        return advertiseForMainRepository.getAdvertiseMainsByDeliverySiteIdx(delivery_site_idx);
    }
}
