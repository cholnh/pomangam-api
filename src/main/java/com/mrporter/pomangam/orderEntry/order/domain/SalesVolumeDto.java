package com.mrporter.pomangam.orderEntry.order.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class SalesVolumeDto {
    private Time arrival_time;

    private Integer sv;

    public SalesVolumeDto(Time arrival_time, Integer sv) {
        this.arrival_time = arrival_time;
        this.sv = sv;
    }
}
