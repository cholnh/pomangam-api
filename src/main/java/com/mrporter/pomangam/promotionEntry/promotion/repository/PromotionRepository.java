package com.mrporter.pomangam.promotionEntry.promotion.repository;

import com.mrporter.pomangam.promotionEntry.promotion.domain.PromotionSumDto;
import org.springframework.data.repository.query.Param;

public interface PromotionRepository {
    PromotionSumDto getSumByStoreIdx(@Param("storeIdx") Integer store_idx);
}
