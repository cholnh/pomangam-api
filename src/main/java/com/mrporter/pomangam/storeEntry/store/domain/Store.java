package com.mrporter.pomangam.storeEntry.store.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

@Table(name = "store_tbl")
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

    private Integer cnt_comment;

    private Time minimum_time;

    private Short parallel_production;

    private Integer maximum_production;

    private Timestamp register_date;

    private Timestamp modify_date;

    private Integer sequence;

    private Byte type;

    @Builder
    public Store(String name, String location, String main_phone_number, String description, Integer cnt_like, Integer cnt_comment, Time minimum_time, Short parallel_production, Integer maximum_production, Timestamp register_date, Timestamp modify_date, Integer sequence, Byte type) {
        this.name = name;
        this.location = location;
        this.main_phone_number = main_phone_number;
        this.description = description;
        this.cnt_like = cnt_like;
        this.cnt_comment = cnt_comment;
        this.minimum_time = minimum_time;
        this.parallel_production = parallel_production;
        this.maximum_production = maximum_production;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.sequence = sequence;
        this.type = type;
    }
}
