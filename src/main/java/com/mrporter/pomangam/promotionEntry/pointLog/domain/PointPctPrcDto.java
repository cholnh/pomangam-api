package com.mrporter.pomangam.promotionEntry.pointLog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointPctPrcDto {
    Integer pct;
    Integer prc;

    public PointPctPrcDto(Integer pct, Integer prc) {
        this.pct = pct;
        this.prc = prc;
    }
}
