package com.mrporter.pomangam.storeEntry.scheduleForStore.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ScheduleForStoreDto implements Serializable {

    private Integer idx;

    private Integer store_idx;

    private Time open_time;

    private Time close_time;

    private Byte state_active;

    private Byte state_pause;

    private String pause_description;

    private Byte first_week_close;

    private Byte second_week_close;

    private Byte third_week_close;

    private Byte fourth_week_close;

    public ScheduleForStore toEntity() {
        return null;
    }
}