package com.mrporter.pomangam.promotionEntry.promotion.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "promotion_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Promotion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String name;

    private Integer discount_prc;

    private Short discount_pct;

    private Date begin_date;

    private Date end_date;

    private Date register_date;

    private Byte state_active;

    @Builder
    public Promotion(String name, Integer discount_prc, Short discount_pct, Date begin_date, Date end_date, Date register_date, Byte state_active) {
        this.name = name;
        this.discount_prc = discount_prc;
        this.discount_pct = discount_pct;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.register_date = register_date;
        this.state_active = state_active;
    }
}
