package com.mrporter.pomangam.orderEntry.order.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class OrderDto implements Serializable {

    private Integer idx;

    private Integer box_no;

    private Integer customer_idx;

    private Integer guest_idx;

    private Integer employee_idx;

    private Integer delivery_site_idx;

    private Integer detail_site_idx;

    private Byte type_payment;

    private Byte state_order;

    private Timestamp register_date;

    private Date arrival_date_only;

    private Time arrival_time_only;

    private Integer using_point;

    private Integer using_coupon_idx;

    private Integer final_amount;

    private String merchant_uid;

    private String imp_uid;

    public OrderDto(Integer idx, Integer box_no, Integer customer_idx, Integer guest_idx, Integer employee_idx, Integer delivery_site_idx, Integer detail_site_idx, Byte type_payment, Byte state_order, Timestamp register_date, Date arrival_date_only, Time arrival_time_only, Integer using_point, Integer using_coupon_idx, Integer final_amount, String merchant_uid, String imp_uid) {
        this.idx = idx;
        this.box_no = box_no;
        this.customer_idx = customer_idx;
        this.guest_idx = guest_idx;
        this.employee_idx = employee_idx;
        this.delivery_site_idx = delivery_site_idx;
        this.detail_site_idx = detail_site_idx;
        this.type_payment = type_payment;
        this.state_order = state_order;
        this.register_date = register_date;
        this.arrival_date_only = arrival_date_only;
        this.arrival_time_only = arrival_time_only;
        this.using_point = using_point;
        this.using_coupon_idx = using_coupon_idx;
        this.final_amount = final_amount;
        this.merchant_uid = merchant_uid;
        this.imp_uid = imp_uid;
    }
}