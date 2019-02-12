package com.mrporter.pomangam.orderEntry.orderTime.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class OrderTimeDto implements Serializable {

    private Integer idx;

    private Integer delivery_site_idx;

    private Integer store_idx;

    private Short offset;

    private Byte state_pause;

    private Time start_time;

    private Time end_time;

    private Time arrival_time;

    private Byte arrival_tomorrow;

    public OrderTime toEntity() {
        return null;
    }
}