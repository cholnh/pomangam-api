package com.mrporter.pomangam.client.services.faq;

import com.mrporter.pomangam.client.domains.faq.FaqCategoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqService {
    List<FaqCategoryDto> findByIdxDeliverySite(Long dIdx, Pageable pageable);
}
