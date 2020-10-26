package com.mrporter.pomangam.client.services.faq;

import com.mrporter.pomangam.client.domains.faq.FaqCategory;
import com.mrporter.pomangam.client.domains.faq.FaqCategoryDto;
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
    public List<FaqCategoryDto> findByIdxDeliverySite(Long dIdx, Pageable pageable) {
        List<FaqCategory> faqs = faqRepo.findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(dIdx, pageable).getContent();
        return FaqCategoryDto.fromEntities(faqs);
    }
}