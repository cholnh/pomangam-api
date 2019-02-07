package com.mrporter.pomangam.order.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class OrderDto implements Serializable {

    private Long idx;

    private Long box_no;

    private Long customer_idx;;

    private Long employee_idx;

    private Long delivery_site_idx;

    private Integer mode;

    private Integer state_order;

    private Integer total;

    private Date register_date;

    private Date arrival_date_only;

    private Time arrival_time_only;

    public Order toEntity() {
        return null;
    }
}