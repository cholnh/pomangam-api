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

    private Integer delivery_site_idx;

    private String name;

    private String location;

    private Integer sequence;

    private Time offset_arrival_time;

    public DetailForDeliverySite toEntity() {
        return null;
    }
}