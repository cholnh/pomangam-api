package com.mrporter.pomangam.orderEntry.order.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class OrderTimeSalesVolumeDto {
    private Time order_deadline;

    private Byte state_pause;

    private Time arrival_time;

    private Integer sv;

    public OrderTimeSalesVolumeDto(Time order_deadline, Byte state_pause, Time arrival_time, BigDecimal sv) {
        this.order_deadline = order_deadline;
        this.state_pause = state_pause;
        this.arrival_time = arrival_time;
        this.sv = sv==null?0:sv.intValue();
    }
}
