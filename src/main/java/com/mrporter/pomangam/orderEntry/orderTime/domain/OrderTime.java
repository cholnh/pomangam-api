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

    @Column(name = "delivery_site_idx")
    private Integer deliverySiteIdx;

    @Column(name = "store_idx")
    private Integer storeIdx;

    @Column(name = "state_pause")
    private Byte statePause;

    @Column(name = "order_deadline")
    private Time orderDeadline;

    @Column(name = "arrival_time")
    private Time arrivalTime;

    @Column(name = "arrival_tomorrow")
    private Byte arrivalTomorrow;

    private Short sequence;

    private String pause_description;

    @Builder
    public OrderTime(Integer deliverySiteIdx, Integer storeIdx, Byte statePause, Time orderDeadline, Time arrivalTime, Byte arrivalTomorrow, Short sequence, String pause_description) {
        this.deliverySiteIdx = deliverySiteIdx;
        this.storeIdx = storeIdx;
        this.statePause = statePause;
        this.orderDeadline = orderDeadline;
        this.arrivalTime = arrivalTime;
        this.arrivalTomorrow = arrivalTomorrow;
        this.sequence = sequence;
        this.pause_description = pause_description;
    }
}
