package com.mrporter.pomangam.orderEntry.order.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class OrderInputDto implements Serializable {

    private Integer customer_idx;

    private Integer guest_idx;

    private Integer delivery_site_idx;

    private Integer detail_site_idx;

    private Byte type_payment;

    private Date arrival_date_only;

    private Time arrival_time_only;

    /* 비회원 */
    private String phone_number;

}