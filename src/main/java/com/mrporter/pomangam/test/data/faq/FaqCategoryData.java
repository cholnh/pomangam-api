package com.mrporter.pomangam.test.data.faq;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.faq.Faq;
import com.mrporter.pomangam.client.domains.faq.FaqCategory;
import com.mrporter.pomangam.client.domains.faq.FaqCategoryMapper;
import com.mrporter.pomangam.client.repositories.faq.FaqCategoryJpaRepository;
import com.mrporter.pomangam.client.repositories.faq.FaqCategoryMapperJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FaqCategoryData {

    @Autowired
    FaqCategoryJpaRepository faqJpaRepository;

    @Autowired
    FaqCategoryMapperJpaRepository faqMapperJpaRepository;

    @Transactional
    public void of(Long idx, Long dIdx, String title, Faq...faqs) {
        FaqCategory faqCategory = FaqCategory.builder()
                .idx(idx)
                .title(title)
                .build();

        for(Faq faq : faqs) {
            faqCategory.addFaq(faq);
        }
        faqJpaRepository.save(faqCategory);

        FaqCategoryMapper faqMapper = FaqCategoryMapper.builder()
                .deliverySite(DeliverySite.builder().idx(dIdx).build())
                .faqCategory(faqCategory)
                .build();
        faqMapperJpaRepository.save(faqMapper);
    }
}