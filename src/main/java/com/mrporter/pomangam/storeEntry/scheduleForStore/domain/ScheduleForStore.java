package com.mrporter.pomangam.storeEntry.scheduleForStore.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Table(name = "schedule_for_store_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class ScheduleForStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "store_idx")
    private Integer storeIdx;

    private Time open_time;

    private Time close_time;

    private Byte state_active;

    private Byte state_pause;

    private String pause_description;

    private Byte first_week_close;

    private Byte second_week_close;

    private Byte third_week_close;

    private Byte fourth_week_close;

    @Builder
    public ScheduleForStore(Integer storeIdx, Time open_time, Time close_time, Byte state_active, Byte state_pause, String pause_description, Byte first_week_close, Byte second_week_close, Byte third_week_close, Byte fourth_week_close) {
        this.storeIdx = storeIdx;
        this.open_time = open_time;
        this.close_time = close_time;
        this.state_active = state_active;
        this.state_pause = state_pause;
        this.pause_description = pause_description;
        this.first_week_close = first_week_close;
        this.second_week_close = second_week_close;
        this.third_week_close = third_week_close;
        this.fourth_week_close = fourth_week_close;
    }
}
