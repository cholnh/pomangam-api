package com.mrporter.pomangam.test.mapObserver.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class MapDto {

    Integer employee_idx;

    Double longitude;

    Double latitude;

    Float direction;

    Byte state;

    @Builder
    public MapDto(Integer employee_idx, Double longitude, Double latitude, Float direction, Byte state) {
        this.employee_idx = employee_idx;
        this.longitude = longitude;
        this.latitude = latitude;
        this.direction = direction;
        this.state = state;
    }
}
