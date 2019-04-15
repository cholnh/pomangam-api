package com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Table(name = "count_search_delivery_site_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class CountSearchDeliverySite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "delivery_site_idx")
    private Integer deliverySiteIdx;

    private long count;

    @Builder
    public CountSearchDeliverySite(Integer deliverySiteIdx, BigInteger count) {
        this.deliverySiteIdx = deliverySiteIdx;
        this.count = count==null?0:count.longValue();
    }
}
