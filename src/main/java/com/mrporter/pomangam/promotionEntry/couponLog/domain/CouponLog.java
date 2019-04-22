package com.mrporter.pomangam.promotionEntry.couponLog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "log_for_coupon_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class CouponLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer employee_idx;

    private Integer coupon_idx;

    private Integer customer_idx;

    private Integer guest_idx;

    private Integer order_idx;

    private Timestamp register_date;

    private Byte type;

    private Integer sequence;

    @Builder
    public CouponLog(Integer employee_idx, Integer coupon_idx, Integer customer_idx, Integer guest_idx, Integer order_idx, Timestamp register_date, Byte type, Integer sequence) {
        this.employee_idx = employee_idx;
        this.coupon_idx = coupon_idx;
        this.customer_idx = customer_idx;
        this.guest_idx = guest_idx;
        this.order_idx = order_idx;
        this.register_date = register_date;
        this.type = type;
        this.sequence = sequence;
    }
}
