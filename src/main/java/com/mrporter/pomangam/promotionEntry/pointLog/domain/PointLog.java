package com.mrporter.pomangam.promotionEntry.pointLog.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "log_for_point_tbl")
@NoArgsConstructor
@Data
@Entity
public class PointLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer employee_idx;

    private Integer customer_idx;

    private Integer order_idx;

    private Integer pre_prc;

    private Integer post_prc;

    private Integer using_point;

    private Timestamp register_date;

    private Byte type;

    private String contents;

    private Integer sequence;

    @Builder
    public PointLog(Integer employee_idx, Integer customer_idx, Integer order_idx, Integer pre_prc, Integer post_prc, Integer using_point, Timestamp register_date, Byte type, String contents, Integer sequence) {
        this.employee_idx = employee_idx;
        this.customer_idx = customer_idx;
        this.order_idx = order_idx;
        this.pre_prc = pre_prc;
        this.post_prc = post_prc;
        this.using_point = using_point;
        this.register_date = register_date;
        this.type = type;
        this.contents = contents;
        this.sequence = sequence;
    }
}
