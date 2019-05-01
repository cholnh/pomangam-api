package com.mrporter.pomangam.promotionEntry.event.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "event_tbl")
@NoArgsConstructor
@Data
@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String title;

    private String contents;

    private Timestamp begin_date;

    private Timestamp end_date;

    private Timestamp register_date;

    private Timestamp modify_date;

    private Byte state_active;

    private String url;

    @Builder
    public Event(String title, String contents, Timestamp begin_date, Timestamp end_date, Timestamp register_date, Timestamp modify_date, Byte state_active, String url) {
        this.title = title;
        this.contents = contents;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.state_active = state_active;
        this.url = url;
    }
}
