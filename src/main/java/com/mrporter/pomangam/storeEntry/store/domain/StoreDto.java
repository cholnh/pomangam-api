package com.mrporter.pomangam.storeEntry.store.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class StoreDto implements Serializable {

    private Integer idx;

    private String name;

    private String location;

    private String main_phone_number;

    private String description;

    private Integer cnt_like;

    private Time minimum_time;

    private Short parallel_production;

    private Integer maximum_production;

    public Store toEntity() {
        return null;
    }
}