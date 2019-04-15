package com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.service;

import com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.repository.CountSearchDeliverySiteRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountSearchDeliverySiteServiceImpl implements CountSearchDeliverySiteService {

    CountSearchDeliverySiteRepositoryImpl countSearchDeliverySiteRepository;

    @Override
    public void addCount(Integer deliverySiteIdx) {
        countSearchDeliverySiteRepository.addCount(deliverySiteIdx);
    }

}
