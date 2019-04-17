package com.mrporter.pomangam.orderEntry.orderTime.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;

@NoArgsConstructor
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

    private String pause_description;

    public OrderTimeDto(Integer idx, Integer delivery_site_idx, Integer store_idx, Byte state_pause, Time order_deadline, Time arrival_time, Byte arrival_tomorrow, Short sequence, String pause_description) {
        this.idx = idx;
        this.delivery_site_idx = delivery_site_idx;
        this.store_idx = store_idx;
        this.state_pause = state_pause;
        this.order_deadline = order_deadline;
        this.arrival_time = arrival_time;
        this.arrival_tomorrow = arrival_tomorrow;
        this.sequence = sequence;
        this.pause_description = pause_description;
    }
}