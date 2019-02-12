package com.mrporter.pomangam.promotionEntry.couponLog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "log_for_coupon_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class CouponLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer coupon_idx;

    private Integer customer_idx;

    private Integer order_idx;

    private Date register_date;

    private Byte type;

    @Builder
    public CouponLog(Integer coupon_idx, Integer customer_idx, Integer order_idx, Date register_date, Byte type) {
        this.coupon_idx = coupon_idx;
        this.customer_idx = customer_idx;
        this.order_idx = order_idx;
        this.register_date = register_date;
        this.type = type;
    }
}
