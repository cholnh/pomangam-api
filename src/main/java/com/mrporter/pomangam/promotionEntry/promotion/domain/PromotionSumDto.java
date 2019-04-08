package com.mrporter.pomangam.promotionEntry.promotion.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PromotionSumDto implements Serializable {

    private Integer sum_prc;

    private Integer sum_pct;

    public PromotionSumDto(BigDecimal sum_prc, BigDecimal sum_pct) {
        this.sum_prc = sum_prc==null?0:sum_prc.intValue();
        this.sum_pct = sum_pct==null?0:sum_pct.intValue();
    }
}