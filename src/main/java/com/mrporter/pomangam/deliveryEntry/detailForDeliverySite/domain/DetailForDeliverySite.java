package com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Table(name = "detail_for_delivery_site_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class DetailForDeliverySite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer delivery_site_idx;

    private String name;

    private String location;

    private Integer sequence;

    private Time offset_arrival_time;

    @Builder
    public DetailForDeliverySite(Integer delivery_site_idx, String name, String location, Integer sequence, Time offset_arrival_time) {
        this.delivery_site_idx = delivery_site_idx;
        this.name = name;
        this.location = location;
        this.sequence = sequence;
        this.offset_arrival_time = offset_arrival_time;
    }
}
