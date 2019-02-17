package com.mrporter.pomangam.orderEntry.orderTime.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    private Byte state_pause;

    @NotBlank
    private Time order_deadline;

    @NotBlank
    private Time arrival_time;

    private Byte arrival_tomorrow;

    private Short sequence;

    @Builder
    public OrderTime(Integer delivery_site_idx, Integer store_idx, Byte state_pause, Time order_deadline, Time arrival_time, Byte arrival_tomorrow, Short sequence) {
        this.delivery_site_idx = delivery_site_idx;
        this.store_idx = store_idx;
        this.state_pause = state_pause;
        this.order_deadline = order_deadline;
        this.arrival_time = arrival_time;
        this.arrival_tomorrow = arrival_tomorrow;
        this.sequence = sequence;
    }
}
