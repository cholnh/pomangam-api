package com.mrporter.pomangam.promotionEntry.coupon.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CouponDto implements Serializable {

    private Integer idx;

    private String name;

    private Integer discount_prc;

    private Short discount_pct;

    private Date begin_date;

    private Date end_date;
}
