package com.mrporter.pomangam.orderEntry.cart.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "cart_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "customer_idx")
    private Integer customerIdx;

    @Column(name = "detail_site_idx")
    private Integer detailSiteIdx;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Builder
    public Cart(Integer customerIdx, Integer detailSiteIdx, Date arrivalDate) {
        this.customerIdx = customerIdx;
        this.detailSiteIdx = detailSiteIdx;
        this.arrivalDate = arrivalDate;
    }
}
