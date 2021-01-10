package com.mrporter.pomangam.client.services.promotion;

import com.mrporter.pomangam.client.domains.promotion.PromotionDto;
import com.mrporter.pomangam.client.repositories.promotion.PromotionJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    PromotionJpaRepository promotionJpaRepository;

    public List<PromotionDto> findByIdxDeliverySite(Long dIdx) {
        return PromotionDto.fromEntities(promotionJpaRepository.findByIdxDeliverySite(dIdx));
    }
}
