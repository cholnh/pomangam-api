package com.mrporter.pomangam.productEntry.product.repository;

import com.mrporter.pomangam.productEntry.product.domain.ProductWithCostDto;
import com.mrporter.pomangam.promotionEntry.promotion.domain.PromotionSumDto;
import com.mrporter.pomangam.promotionEntry.promotion.repository.PromotionRepositoryImpl;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    EntityManager em;

    PromotionRepositoryImpl promotionRepository;

    @Override
    public List<ProductWithCostDto> findByStoreIdx(Integer store_idx) {
        if(store_idx == null) {
            return null;
        }
        PromotionSumDto promotionSumDto = promotionRepository.getSumByStoreIdx(store_idx);

        int sum_prc = promotionSumDto.getSum_prc() == null ? 0 : promotionSumDto.getSum_prc();
        int sum_pct = promotionSumDto.getSum_pct() == null ? 0 : promotionSumDto.getSum_pct();

        Query nativeQuery1 = em.createNativeQuery(

                "SELECT  " +
                        "    p.idx, p.store_idx, p.name, p.description, p.sub_description, p.category_id, p.category_name, p.state_active, p.type, p.cnt_like, p.register_date, p.modify_date, p.sequence, " +
                        "    CAST((c.unit_cost + c.c_commission_prc + (c.unit_cost * c.c_commission_pct / 100)) " +
                        "       - " + sum_prc + " - (c.unit_cost * " + sum_pct + " / 100) " +
                        "        AS SIGNED INTEGER) AS final_cost " +
                        "FROM " +
                        "    product_tbl p, " +
                        "    cost_tbl c " +
                        "WHERE " +
                        "    p.idx = c.product_idx " +
                        "    AND store_idx = ? " +
                        "    AND p.state_active = 1 " +
                        "ORDER BY sequence DESC "


        );
        nativeQuery1.setParameter(1, store_idx);
        List<ProductWithCostDto> productWithCostDtoList = new JpaResultMapper().list(nativeQuery1, ProductWithCostDto.class);
        return productWithCostDtoList;
    }
}
