package com.mrporter.pomangam.promotionEntry.couponLog.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CouponLogDto implements Serializable {

    private Integer idx;

    private Integer employee_idx;

    private Integer coupon_idx;

    private Integer customer_idx;

    private Integer order_idx;

    private Timestamp register_date;

    private Byte type;

    private Integer sequence;

    public CouponLogDto(Integer idx, Integer employee_idx, Integer coupon_idx, Integer customer_idx, Integer order_idx, Timestamp register_date, Byte type, Integer sequence) {
        this.idx = idx;
        this.employee_idx = employee_idx;
        this.coupon_idx = coupon_idx;
        this.customer_idx = customer_idx;
        this.order_idx = order_idx;
        this.register_date = register_date;
        this.type = type;
        this.sequence = sequence;
    }
}