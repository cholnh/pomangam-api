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

    @Column(name = "delivery_site_idx")
    private Integer deliverySiteIdx;

    private String name;

    private String location;

    private Integer sequence;

    @Column(name = "offset_arrival_time")
    private Time offsetArrivalTime;

    private Double latitude;

    private Double longitude;

    private String abbreviation;

    @Builder
    public DetailForDeliverySite(Integer deliverySiteIdx, String name, String location, Integer sequence, Time offsetArrivalTime, Double latitude, Double longitude, String abbreviation) {
        this.deliverySiteIdx = deliverySiteIdx;
        this.name = name;
        this.location = location;
        this.sequence = sequence;
        this.offsetArrivalTime = offsetArrivalTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.abbreviation = abbreviation;
    }
}
