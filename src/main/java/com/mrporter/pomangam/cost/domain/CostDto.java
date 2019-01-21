package com.mrporter.pomangam.cost.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CostDto implements Serializable {

    private Long idx;

    private Long product_idx;

    private Long promotion_idx;

    private Long unit_cost;

    private Long s_commission_prc;

    private Integer s_commission_pct;

    private Long c_commission_prc;

    private Integer c_commission_pct;

    public Cost toEntity(Long product_idx, Long promotion_idx, Long unit_cost, Long s_commission_prc, Integer s_commission_pct, Long c_commission_prc, Integer c_commission_pct) {
        return Cost.builder()
                .product_idx(product_idx)
                .promotion_idx(promotion_idx)
                .unit_cost(unit_cost)
                .s_commission_pct(s_commission_pct)
                .s_commission_prc(s_commission_prc)
                .c_commission_pct(c_commission_pct)
                .c_commission_prc(c_commission_prc)
                .build();
    }
}
