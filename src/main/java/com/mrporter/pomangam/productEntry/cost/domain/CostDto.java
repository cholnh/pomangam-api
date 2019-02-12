package com.mrporter.pomangam.productEntry.cost.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CostDto implements Serializable {

    private Integer idx;

    private Integer product_idx;

    private Integer promotion_idx;

    private Integer unit_cost;

    private Integer s_commission_prc;

    private Short s_commission_pct;

    private Integer c_commission_prc;

    private Short c_commission_pct;
}
