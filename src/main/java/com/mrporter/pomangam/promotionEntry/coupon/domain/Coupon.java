package com.mrporter.pomangam.promotionEntry.coupon.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "coupon_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Coupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String name;

    private Integer discount_prc;

    private Short discount_pct;

    private Date begin_date;

    private Date end_date;

    @Builder
    public Coupon(String name, Integer discount_prc, Short discount_pct, Date begin_date, Date end_date) {
        this.name = name;
        this.discount_prc = discount_prc;
        this.discount_pct = discount_pct;
        this.begin_date = begin_date;
        this.end_date = end_date;
    }
}