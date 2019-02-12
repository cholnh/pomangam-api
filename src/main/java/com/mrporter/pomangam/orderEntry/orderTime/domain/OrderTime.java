package com.mrporter.pomangam.orderEntry.orderTime.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Table(name = "ordertime_for_store_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class OrderTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer delivery_site_idx;

    private Integer store_idx;

    private Short offset;

    private Byte state_pause;

    private Time start_time;

    private Time end_time;

    private Time arrival_time;

    private Byte arrival_tomorrow;

    @Builder
    public OrderTime(Integer delivery_site_idx, Integer store_idx, Short offset, Byte state_pause, Time start_time, Time end_time, Time arrival_time, Byte arrival_tomorrow) {
        this.delivery_site_idx = delivery_site_idx;
        this.store_idx = store_idx;
        this.offset = offset;
        this.state_pause = state_pause;
        this.start_time = start_time;
        this.end_time = end_time;
        this.arrival_time = arrival_time;
        this.arrival_tomorrow = arrival_tomorrow;
    }
}
