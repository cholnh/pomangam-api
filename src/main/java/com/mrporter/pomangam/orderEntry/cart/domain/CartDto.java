package com.mrporter.pomangam.orderEntry.cart.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class CartDto implements Serializable {

    private Integer idx;

    private Integer customerIdx;

    private Integer detailSiteIdx;

    private LocalDateTime arrivalDate;

    public CartDto(Integer idx, Integer customerIdx, Integer detailSiteIdx, Timestamp arrivalDate) {
        this.idx = idx;
        this.customerIdx = customerIdx;
        this.detailSiteIdx = detailSiteIdx;
        this.arrivalDate = arrivalDate.toLocalDateTime();
    }

    public Cart toEntity() {
        return Cart
                .builder()
                .idx(idx)
                .customerIdx(customerIdx)
                .detailSiteIdx(detailSiteIdx)
                .arrivalDate(Timestamp.valueOf(arrivalDate))
                .build();
    }
}