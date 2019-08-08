package com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.service;

import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.domain.SubAdvertiseForMainDto;
import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.repository.SubAdvertiseForMainRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubAdvertiseForMainServiceImpl implements SubAdvertiseForMainService {

    SubAdvertiseForMainRepositoryImpl subAdvertiseForMainRepository;

    @Cacheable(value="getSubAdvertiseMainCache", key="#delivery_site_idx")
    @Override
    public List<SubAdvertiseForMainDto> getSubAdvertiseMainsByDeliverySiteIdx(Integer delivery_site_idx) {
        return subAdvertiseForMainRepository.getSubAdvertiseMainsByDeliverySiteIdx(delivery_site_idx);
    }
}
