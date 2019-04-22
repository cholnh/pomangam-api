package com.mrporter.pomangam.productEntry.product.repository;

import com.mrporter.pomangam.common.util.sqlInjection.SqlInjection;
import com.mrporter.pomangam.productEntry.product.domain.AdditionalDto;
import com.mrporter.pomangam.productEntry.product.domain.ProductDto;
import com.mrporter.pomangam.productEntry.product.domain.ProductSummaryDto;
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
    SqlInjection sqlInjection;

    @Override
    public ProductWithCostDto findByProductIdx(Integer product_idx) {
        if(product_idx == null) {
            return null;
        }

        Query nativeQuery1 = em
                .createNativeQuery("SELECT * FROM product_tbl WHERE idx = ?")
                .setParameter(1, product_idx);
        List<ProductDto> productDtoList = new JpaResultMapper().list(nativeQuery1, ProductDto.class);
        if(productDtoList.isEmpty()) {
            return null;
        }

        ProductDto productDto = productDtoList.get(0);
        PromotionSumDto promotionSumDto = promotionRepository.getSumByStoreIdx(productDto.getStore_idx());

        int sum_prc = promotionSumDto.getSum_prc() == null ? 0 : promotionSumDto.getSum_prc();
        int sum_pct = promotionSumDto.getSum_pct() == null ? 0 : promotionSumDto.getSum_pct();

        Query nativeQuery2 = em
                .createNativeQuery(
                "SELECT  " +
                        "    p.idx, p.store_idx, p.name, p.description, p.sub_description, p.category_id, p.category_name, p.state_active, p.type, p.cnt_like, p.register_date, p.modify_date, p.sequence,  " +
                        "    CAST(c.unit_cost + c.c_commission_prc + (c.unit_cost * c.c_commission_pct / 100) AS SIGNED INTEGER) AS prime_cost, " +
                        //"    CAST((c.unit_cost + c.c_commission_prc + (c.unit_cost * c.c_commission_pct / 100)) " +
                        //"       - " + sum_prc + " - (c.unit_cost * " + sum_pct + " / 100) " +
                        "    CAST((c.unit_cost + c.c_commission_prc + (c.unit_cost * c.c_commission_pct / 100)) * " +
                        "       (1 - " + sum_pct + " / 100) - " + sum_prc +
                        "        AS SIGNED INTEGER) AS final_cost, img.imgpath " +
                        "FROM " +
                        "    cost_tbl c, " +
                        "    product_tbl p " +
                        "LEFT OUTER JOIN imgpath_for_product_tbl img " +
                        "ON img.product_idx = p.idx AND img.type = 0 " +
                        "WHERE " +
                        "    p.idx = c.product_idx " +
                        "    AND p.idx = ? " +
                        "    AND p.state_active = 1 " +
                        "ORDER BY sequence DESC ")
                .setParameter(1, product_idx);

        //ProductWithCostDto productWithCostDto = new JpaResultMapper().uniqueResult(nativeQuery2, ProductWithCostDto.class);
        List<ProductWithCostDto> productWithCostDtoList = new JpaResultMapper().list(nativeQuery2, ProductWithCostDto.class);
        if(productWithCostDtoList.isEmpty()) {
            return null;
        } else {
            return productWithCostDtoList.get(0);
        }
    }

    @Override
    public List<ProductWithCostDto> findByStoreIdx(Integer store_idx, Integer type, String orderBy) {
        if(store_idx == null) {
            return null;
        }

        PromotionSumDto promotionSumDto = promotionRepository.getSumByStoreIdx(store_idx);

        int sum_prc = promotionSumDto.getSum_prc() == null ? 0 : promotionSumDto.getSum_prc();
        int sum_pct = promotionSumDto.getSum_pct() == null ? 0 : promotionSumDto.getSum_pct();

        Query nativeQuery1 = em
                .createNativeQuery(
                "SELECT  " +
                        "    p.idx, p.store_idx, p.name, p.description, p.sub_description, p.category_id, p.category_name, p.state_active, p.type, p.cnt_like, p.register_date, p.modify_date, p.sequence, " +
                        "    CAST(c.unit_cost + c.c_commission_prc + (c.unit_cost * c.c_commission_pct / 100) AS SIGNED INTEGER) AS prime_cost, " +
                        //"    CAST((c.unit_cost + c.c_commission_prc + (c.unit_cost * c.c_commission_pct / 100)) " +
                        //"       - " + sum_prc + " - (c.unit_cost * " + sum_pct + " / 100) " +
                        "    CAST((c.unit_cost + c.c_commission_prc + (c.unit_cost * c.c_commission_pct / 100)) * " +
                        "       (1 - " + sum_pct + " / 100) - " + sum_prc +
                        "        AS SIGNED INTEGER) AS final_cost, img.imgpath  " +
                        "FROM " +
                        "    cost_tbl c, " +
                        "    product_tbl p " +
                        "LEFT OUTER JOIN imgpath_for_product_tbl img " +
                        "ON img.product_idx = p.idx AND img.type = 0 " +
                        "WHERE " +
                        "    p.idx = c.product_idx " +
                        "    AND store_idx = :storeIdx " +
                        "    AND p.state_active = 1 " +
                        (type != null ? "AND p.type = :type ":"AND p.type IN (0, 1, 3) ") +   // 메인, 서브, 음료 (토핑 제외)
                        (orderBy != null && !sqlInjection.isSQLInjection(orderBy) ? "ORDER BY " + orderBy :"ORDER BY sequence DESC "))
                .setParameter("storeIdx", store_idx);

        if(type != null) {
            nativeQuery1.setParameter("type", type);
        }

        List<ProductWithCostDto> productWithCostDtoList = new JpaResultMapper().list(nativeQuery1, ProductWithCostDto.class);
        return productWithCostDtoList;
    }

    @Override
    public List<ProductSummaryDto> findByQuery(String query, Integer delivery_site_idx) {
        Query nativeQuery = em
                .createNativeQuery("SELECT " +
                        "    p.idx as product_idx, p.store_idx as store_idx, s.name as store_name, p.name as product_name, p.category_id as category_id, p.category_name as category_name, p.type as product_type " +
                        "FROM " +
                        "    product_tbl p, " +
                        "    store_tbl s, " +
                        "    schedule_for_store_tbl sc " +
                        "WHERE " +
                        "    s.idx IN (SELECT store_idx FROM ordertime_for_store_tbl WHERE delivery_site_idx = :didx group by store_idx) " +
                        "        AND p.store_idx = s.idx " +
                        "        AND s.idx = sc.store_idx " +
                        "        AND p.name LIKE :name " +
                        "        AND p.state_active = 1 " +
                        "        AND sc.state_active = 1 " +
                        "        AND sc.state_pause = 0 " +
                        "        AND p.type IN (0, 1, 3) ")
                .setParameter("didx", delivery_site_idx)
                .setParameter("name", "%"+query+"%");
        List<ProductSummaryDto> productDtoList = new JpaResultMapper().list(nativeQuery, ProductSummaryDto.class);

        return productDtoList;
    }

    @Override
    public List<AdditionalDto> findAdditionalByType(Integer productIdx, Integer type) {
        Query nativeQuery = em
                .createNativeQuery("SELECT " +
                        "    pd.*, CAST(c.unit_cost + c.c_commission_prc + (c.unit_cost * c.c_commission_pct / 100) AS SIGNED INTEGER) AS prime_cost " +
                        "FROM " +
                        "    product_tbl pd, " +
                        "    additional_for_product_tbl link, " +
                        "    cost_tbl c " +
                        "WHERE " +
                        "   link.product_idx = :productIdx " +
                        "    AND pd.idx = link.map_product_idx " +
                        "    AND pd.idx = c.product_idx " +
                        "    AND state_active = 1 " +
                        "    AND pd.type = :type " +
                        "    ORDER BY sequence DESC ")
                .setParameter("productIdx", productIdx)
                .setParameter("type", type);
        List<AdditionalDto> productDtoList = new JpaResultMapper().list(nativeQuery, AdditionalDto.class);

        return productDtoList;
    }
}
