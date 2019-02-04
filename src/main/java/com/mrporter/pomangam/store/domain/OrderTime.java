package com.mrporter.pomangam.store.domain;

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
    private Long idx;

    private Long delivery_site_idx;

    private Long store_idx;

    private Integer offset;

    private Integer state_pause;

    private Time start_time;

    private Time end_time;

    private Time arrival_time;

    private Integer arrival_tomorrow;

    @Builder
    public OrderTime(Long delivery_site_idx, Long store_idx, Integer offset, Integer state_pause, Time start_time, Time end_time, Time arrival_time, Integer arrival_tomorrow) {
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
