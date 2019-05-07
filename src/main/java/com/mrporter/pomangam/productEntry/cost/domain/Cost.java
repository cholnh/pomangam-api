package com.mrporter.pomangam.productEntry.cost.domain;

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
    private Integer idx;

    private Integer product_idx;

    private Integer unit_cost;

    private Integer s_commission_prc;

    private Short s_commission_pct;

    private Integer c_commission_prc;

    private Short c_commission_pct;

    @Builder
    public Cost(Integer product_idx, Integer unit_cost, Integer s_commission_prc, Short s_commission_pct, Integer c_commission_prc, Short c_commission_pct) {
        this.product_idx = product_idx;
        this.unit_cost = unit_cost;
        this.s_commission_prc = s_commission_prc;
        this.s_commission_pct = s_commission_pct;
        this.c_commission_prc = c_commission_prc;
        this.c_commission_pct = c_commission_pct;
    }
}
