package com.mrporter.pomangam.promotionEntry.couponLog.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CouponLogDto implements Serializable {

    private Integer idx;

    private Integer coupon_idx;

    private Integer customer_idx;

    private Integer order_idx;

    private Date register_date;

    private Byte type;

    public CouponLog toEntity() {
        return null;
    }
}