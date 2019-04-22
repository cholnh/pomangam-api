package com.mrporter.pomangam.orderEntry.cart.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


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

    @Column(name = "guest_idx")
    private Integer guestIdx;

    @Column(name = "detail_site_idx")
    private Integer detailSiteIdx;

    @Column(name = "arrival_date")
    private Timestamp arrivalDate;

    @Builder
    public Cart(Integer idx, Integer customerIdx, Integer guestIdx, Integer detailSiteIdx, Timestamp arrivalDate) {
        this.idx = idx;
        this.customerIdx = customerIdx;
        this.guestIdx = guestIdx;
        this.detailSiteIdx = detailSiteIdx;
        this.arrivalDate = arrivalDate;
    }
}
