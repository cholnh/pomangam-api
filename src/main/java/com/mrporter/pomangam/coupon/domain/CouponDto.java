package com.mrporter.pomangam.coupon.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CouponDto implements Serializable {

    private Long idx;

    private String name;

    private Long discount_prc;

    private Integer discount_pct;

    private Date begin_date;

    private Date end_date;

    public Coupon toEntity(String name, Long discount_prc, Integer discount_pct, Date begin_date, Date end_date) {
        return Coupon.builder()
                .name(name)
                .discount_prc(discount_prc)
                .discount_pct(discount_pct)
                .begin_date(begin_date)
                .end_date(end_date)
                .build();
    }
}
