package com.mrporter.pomangam.test.mapObserver.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "map_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Map {
    @Id
    Integer employee_idx;

    Double longitude;

    Double latitude;

    Float direction;

    Byte state;

    @Builder
    public Map(Integer employee_idx, Double longitude, Double latitude, Float direction, Byte state) {
        this.employee_idx = employee_idx;
        this.longitude = longitude;
        this.latitude = latitude;
        this.direction = direction;
        this.state = state;
    }
}
