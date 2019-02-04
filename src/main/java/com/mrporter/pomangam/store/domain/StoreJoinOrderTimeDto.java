package com.mrporter.pomangam.store.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class StoreJoinOrderTimeDto implements Serializable {

    /* Store */
    private Long idx;

    private Long delivery_site_idx;

    private String name;

    private String location;

    private String main_phone_number;

    private String description;

    private Integer cnt_like;

    private Time minimum_time;

    private Integer parallel_production;

    /* OrderTime */
    private Time end_time;

    private Integer  state_pause;
}