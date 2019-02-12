package com.mrporter.pomangam.storeEntry.store.domain;

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
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String name;

    private String location;

    private String main_phone_number;

    private String description;

    private Integer cnt_like;

    private Time minimum_time;

    private Short parallel_production;

    private Integer maximum_production;

    @Builder
    public Store(String name, String location, String main_phone_number, String description, Integer cnt_like, Time minimum_time, Short parallel_production, Integer maximum_production) {
        this.name = name;
        this.location = location;
        this.main_phone_number = main_phone_number;
        this.description = description;
        this.cnt_like = cnt_like;
        this.minimum_time = minimum_time;
        this.parallel_production = parallel_production;
        this.maximum_production = maximum_production;
    }
}
