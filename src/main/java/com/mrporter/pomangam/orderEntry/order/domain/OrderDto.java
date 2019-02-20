package com.mrporter.pomangam.orderEntry.order.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class OrderDto implements Serializable {

    private Integer idx;

    private Integer box_no;

    private Integer customer_idx;

    private Integer employee_idx;

    private Integer delivery_site_idx;

    private Byte type_payment;

    private Byte state_order;

    private Date register_date;

    private Date arrival_date_only;

    private Time arrival_time_only;

    public Order toEntity() {
        return null;
    }
}