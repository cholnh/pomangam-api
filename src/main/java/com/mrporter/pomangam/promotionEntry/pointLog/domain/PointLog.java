package com.mrporter.pomangam.promotionEntry.pointLog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "log_for_point_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class PointLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer customer_idx;

    private Integer order_idx;

    private Date register_date;

    private Integer final_prc;

    private Integer use_point;

    private Byte type;

    @Builder
    public PointLog(Integer customer_idx, Integer order_idx, Date register_date, Integer final_prc, Integer use_point, Byte type) {
        this.customer_idx = customer_idx;
        this.order_idx = order_idx;
        this.register_date = register_date;
        this.final_prc = final_prc;
        this.use_point = use_point;
        this.type = type;
    }
}
