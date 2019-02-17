package com.mrporter.pomangam.storeEntry.store.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class InquiryResultDto implements Serializable {

    /* Store */
    private Integer idx;

    private String name;

    private String location;

    private String main_phone_number;

    private String description;

    private Integer cnt_like;

    private Time minimum_time;

    private Short parallel_production;

    private Integer maximum_production;

    /* ScheduleForStore */
    private Time end_time;

    private Byte state_pause;

    /* etc */
    private Integer remaining_capacity;

    private ZonedDateTime arrival_time;

    public InquiryResultDto(Integer idx,
                            String name,
                            String location,
                            String main_phone_number,
                            String description,
                            Integer cnt_like,
                            Time minimum_time,
                            Short parallel_production,
                            Integer maximum_production,
                            Time end_time,
                            Byte state_pause) {
        this.idx = idx;
        this.name = name;
        this.location = location;
        this.main_phone_number = main_phone_number;
        this.description = description;
        this.cnt_like = cnt_like;
        this.minimum_time = minimum_time;
        this.parallel_production = parallel_production;
        this.maximum_production = maximum_production;
        this.end_time = end_time;
        this.state_pause = state_pause;
    }
}