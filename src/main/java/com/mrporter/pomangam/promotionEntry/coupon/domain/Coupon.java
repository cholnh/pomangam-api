package com.mrporter.pomangam.promotionEntry.coupon.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "coupon_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Coupon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer issuer_idx;

    private Integer modifier_idx;

    private String name;

    private Integer discount_prc;

    private Short discount_pct;

    private Timestamp begin_date;

    private Timestamp end_date;

    private Timestamp register_date;

    private Timestamp modify_date;

    private String code;

    private Byte state_active;

    private Integer customer_idx;

    @Builder
    public Coupon(Integer issuer_idx, Integer modifier_idx, String name, Integer discount_prc, Short discount_pct, Timestamp begin_date, Timestamp end_date, Timestamp register_date, Timestamp modify_date, String code, Byte state_active, Integer customer_idx) {
        this.issuer_idx = issuer_idx;
        this.modifier_idx = modifier_idx;
        this.name = name;
        this.discount_prc = discount_prc;
        this.discount_pct = discount_pct;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.code = code;
        this.state_active = state_active;
        this.customer_idx = customer_idx;
    }
}