package com.mrporter.pomangam.advertiseEntry.advertiseForPopup.service;

import com.mrporter.pomangam.advertiseEntry.advertiseForPopup.domain.AdvertiseForPopupDto;
import com.mrporter.pomangam.advertiseEntry.advertiseForPopup.repository.AdvertiseForPopupRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdvertiseForPopupServiceImpl implements AdvertiseForPopupService {
    AdvertiseForPopupRepositoryImpl advertiseForPopupRepository;


    @Cacheable(value="getAdvertisePopupCache", key="#delivery_site_idx")
    @Override
    public List<AdvertiseForPopupDto> getAdvertisePopupsByDeliverySiteIdx(Integer delivery_site_idx) {
        return advertiseForPopupRepository.getAdvertisePopupsByDeliverySiteIdx(delivery_site_idx);
    }
}
