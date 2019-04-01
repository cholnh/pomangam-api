package com.mrporter.pomangam.orderEntry.cart.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CartWithArrivalTimeDto implements Serializable {

    private Integer idx;

    private Integer customerIdx;

    private Integer detailSiteIdx;

    private Date arrivalDate;

    private Time arrivalTime;

    public CartWithArrivalTimeDto(Integer idx, Integer customerIdx, Integer detailSiteIdx, Date arrivalDate, Time arrivalTime) {
        this.idx = idx;
        this.customerIdx = customerIdx;
        this.detailSiteIdx = detailSiteIdx;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
    }
}