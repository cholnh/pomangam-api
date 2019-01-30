package com.mrporter.pomangam.promotion.domain;

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
    private Long idx;

    private String name;

    private Integer discount_prc;

    private Integer discount_pct;

    private Date begin_date;

    private Date end_date;

    private Date register_date;

    @Builder
    public Promotion(String name, Integer discount_prc, Integer discount_pct, Date begin_date, Date end_date, Date register_date) {
        this.name = name;
        this.discount_prc = discount_prc;
        this.discount_pct = discount_pct;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.register_date = register_date;
    }
}
