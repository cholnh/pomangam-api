package com.mrporter.pomangam.client.services.faq;

import com.mrporter.pomangam.client.domains.faq.Faq;
import com.mrporter.pomangam.client.domains.faq.FaqDto;
import com.mrporter.pomangam.client.repositories.faq.FaqJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FaqServiceImpl implements FaqService {

    private FaqJpaRepository faqRepo;

    @Override
    public List<FaqDto> findByIdxDeliverySite(Long dIdx, Pageable pageable) {
        List<Faq> faqs = faqRepo.findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(dIdx, pageable).getContent();
        return FaqDto.fromEntities(faqs);
    }
}