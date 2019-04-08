package com.mrporter.pomangam.promotionEntry.promotion.repository;

import com.mrporter.pomangam.promotionEntry.promotion.domain.PromotionSumDto;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@AllArgsConstructor
public class PromotionRepositoryImpl implements PromotionRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public PromotionSumDto getSumByStoreIdx(Integer store_idx){
        Query nativeQuery1 = em.createNativeQuery(
                "SELECT " +
                        "    SUM(discount_prc) AS sum_prc, SUM(discount_pct) AS sum_pct " +
                        "FROM " +
                        "    promotion_for_store_tbl prl, " +
                        "    promotion_tbl prm " +
                        "WHERE " +
                        "    prl.store_idx = ? " +
                        "        AND prl.promotion_idx = prm.idx " +
                        "        AND prm.begin_date <= now() " +
                        "        AND prm.end_date >= now() "
        );
        nativeQuery1.setParameter(1, store_idx);
        PromotionSumDto promotionSumDto = new JpaResultMapper().uniqueResult(nativeQuery1, PromotionSumDto.class);

        return promotionSumDto;
    }
}
