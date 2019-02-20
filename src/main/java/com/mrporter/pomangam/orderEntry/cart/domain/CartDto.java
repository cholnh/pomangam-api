package com.mrporter.pomangam.orderEntry.cart.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CartDto implements Serializable {

    private Integer idx;

    private Integer customerIdx;

    private Integer detailSiteIdx;

    private Date arrivalDate;

    public CartDto(Integer idx, Integer customerIdx, Integer detailSiteIdx, Date arrivalDate) {
        this.idx = idx;
        this.customerIdx = customerIdx;
        this.detailSiteIdx = detailSiteIdx;
        this.arrivalDate = arrivalDate;
    }

    public Cart toEntity() {
        return null;
    }
}