package com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class DetailForDeliverySiteDto implements Serializable {

    private Integer idx;

    private Integer deliverySiteIdx;

    private String name;

    private String location;

    private Integer sequence;

    private Time offsetArrivalTime;

    public DetailForDeliverySiteDto(Integer idx, Integer deliverySiteIdx, String name, String location, Integer sequence, Time offsetArrivalTime) {
        this.idx = idx;
        this.deliverySiteIdx = deliverySiteIdx;
        this.name = name;
        this.location = location;
        this.sequence = sequence;
        this.offsetArrivalTime = offsetArrivalTime;
    }

    public DetailForDeliverySite toEntity() {
        return null;
    }
}