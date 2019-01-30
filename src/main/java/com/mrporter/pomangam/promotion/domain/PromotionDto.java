package com.mrporter.pomangam.promotion.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PromotionDto implements Serializable {

    private Long idx;

    private String name;

    private Integer discount_prc;

    private Integer discount_pct;

    private Date begin_date;

    private Date end_date;

    private Date register_date;

    public Promotion toEntity() {
        return null;
    }
}