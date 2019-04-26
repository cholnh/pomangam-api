package com.mrporter.pomangam.deliveryEntry.deliverySite.service;

import com.mrporter.pomangam.common.util.choseong.InitialConsonant;
import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteDto;
import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteWithCountDto;
import com.mrporter.pomangam.deliveryEntry.deliverySite.repository.DeliverySiteRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliverySiteServiceImpl implements DeliverySiteService {

    DeliverySiteRepositoryImpl deliverySiteRepository;

    @Override
    public DeliverySiteDto getByDeliverySiteIdx(Integer deliverySiteIdx) {
        return deliverySiteRepository.getByDeliverySiteIdx(deliverySiteIdx);
    }

    @Override
    public List<DeliverySiteWithCountDto> findByQuery(String query) {
        if(InitialConsonant.isInitialConsonants(query)) {
            return deliverySiteRepository.findByConsonantQuery(query);
        } else {
            return deliverySiteRepository.findByQuery(query);
        }

    }

    @Override
    public List<DeliverySiteDto> findByRegionCategoryIdx(Integer regionCategoryIdx) {
        return deliverySiteRepository.findByRegionCategoryIdx(regionCategoryIdx);
    }

}
