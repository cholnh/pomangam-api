package com.mrporter.pomangam.couponLog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "coupon_log_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class CouponLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long coupon_idx;

    private Long customer_idx;

    private Long order_idx;

    private Date register_date;

    private Integer type;

    @Builder
    public CouponLog(Long coupon_idx, Long customer_idx, Long order_idx, Date register_date, Integer type) {
        this.coupon_idx = coupon_idx;
        this.customer_idx = customer_idx;
        this.order_idx = order_idx;
        this.register_date = register_date;
        this.type = type;
    }
}
