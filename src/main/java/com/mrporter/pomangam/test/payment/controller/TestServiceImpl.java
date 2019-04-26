package com.mrporter.pomangam.test.payment.controller;

import com.mrporter.pomangam.common.util.choseong.InitialConsonant;
import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteDto;
import com.mrporter.pomangam.deliveryEntry.deliverySite.repository.DeliverySiteRepositoryImpl;
import com.mrporter.pomangam.deliveryEntry.initialConsonantForDsite.domain.InitialConsonantForDsite;
import com.mrporter.pomangam.deliveryEntry.initialConsonantForDsite.repository.InitialConsonantForDsiteJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    DeliverySiteRepositoryImpl deliverySiteRepository;
    InitialConsonantForDsiteJpaRepository initialConsonantForDsiteJpaRepository;

    public void test() {
        List<DeliverySiteDto> list = deliverySiteRepository.findAll();
        for(DeliverySiteDto site : list) {
            InitialConsonantForDsite initialConsonant = new InitialConsonantForDsite();
            initialConsonant.setInitial_consonant(InitialConsonant.getInitial(site.getName()));
            initialConsonant.setDelivery_site_idx(site.getIdx());
            initialConsonantForDsiteJpaRepository.save(initialConsonant);
        }
    }
}
