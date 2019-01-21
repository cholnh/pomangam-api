package com.mrporter.pomangam.cost.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "cost_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Cost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long product_idx;

    private Long promotion_idx;

    private Long unit_cost;

    private Long s_commission_prc;

    private Integer s_commission_pct;

    private Long c_commission_prc;

    private Integer c_commission_pct;

    @Builder
    public Cost(Long product_idx, Long promotion_idx, Long unit_cost, Long s_commission_prc, Integer s_commission_pct, Long c_commission_prc, Integer c_commission_pct) {
        this.product_idx = product_idx;
        this.promotion_idx = promotion_idx;
        this.unit_cost = unit_cost;
        this.s_commission_prc = s_commission_prc;
        this.s_commission_pct = s_commission_pct;
        this.c_commission_prc = c_commission_prc;
        this.c_commission_pct = c_commission_pct;
    }
}
