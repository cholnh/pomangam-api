package com.mrporter.pomangam.store.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class OrderTimeDto implements Serializable {

    private Long idx;

    private Long delivery_site_idx;

    private Long store_idx;

    private Integer offset;

    private Integer state_pause;

    private Time start_time;

    private Time end_time;

    private Time arrival_time;

    private Integer arrival_tomorrow;

    public OrderTime toEntity() {
        return null;
    }
}