package com.mrporter.pomangam.customerService.faq.service;

import com.mrporter.pomangam.customerService.faq.domain.Faq;
import com.mrporter.pomangam.customerService.faq.repository.FaqJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FaqServiceImpl implements FaqService {

    FaqJpaRepository policyJpaRepository;

    @Override
    public List<Faq> getAll() {
        return policyJpaRepository.findAll();
    }
}
