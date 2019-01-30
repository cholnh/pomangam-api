package com.mrporter.pomangam.pointLog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "point_log_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class PointLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long customer_idx;

    private Long order_idx;

    private Date register_date;

    private Integer final_prc;

    private Integer use_point;

    private Integer type;

    @Builder
    public PointLog(Long customer_idx, Long order_idx, Date register_date, Integer final_prc, Integer use_point, Integer type) {
        this.customer_idx = customer_idx;
        this.order_idx = order_idx;
        this.register_date = register_date;
        this.final_prc = final_prc;
        this.use_point = use_point;
        this.type = type;
    }
}
