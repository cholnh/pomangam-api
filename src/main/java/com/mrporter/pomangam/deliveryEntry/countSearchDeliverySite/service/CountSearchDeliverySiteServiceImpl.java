package com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.service;

import com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.domain.CountSearchDeliverySite;
import com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.repository.CountSearchDeliverySiteJpaRepository;
import com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.repository.CountSearchDeliverySiteRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountSearchDeliverySiteServiceImpl implements CountSearchDeliverySiteService {

    CountSearchDeliverySiteJpaRepository countSearchDeliverySiteJpaRepository;
    CountSearchDeliverySiteRepositoryImpl countSearchDeliverySiteRepository;

    @Override
    public void addCount(Integer deliverySiteIdx) {
        if(countSearchDeliverySiteJpaRepository.existsByDeliverySiteIdx(deliverySiteIdx)) {
            countSearchDeliverySiteRepository.addCount(deliverySiteIdx);
        } else {
            CountSearchDeliverySite entity = CountSearchDeliverySite.builder()
                    .deliverySiteIdx(deliverySiteIdx)
                    .count(null)
                    .build();
            countSearchDeliverySiteJpaRepository.save(entity);
        }

    }

}
