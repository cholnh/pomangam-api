package com.mrporter.pomangam.client.services.faq;

import com.mrporter.pomangam.client.domains.faq.FaqDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqService {
    List<FaqDto> findByIdxDeliverySite(Long dIdx, Pageable pageable);
}
