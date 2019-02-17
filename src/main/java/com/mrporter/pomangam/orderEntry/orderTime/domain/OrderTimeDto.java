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

    private Byte state_pause;

    private Time order_deadline;

    private Time arrival_time;

    private Byte arrival_tomorrow;

    private Short sequence;

    public OrderTime toEntity() {
        return null;
    }
}