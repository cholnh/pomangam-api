package com.mrporter.pomangam.promotionEntry.event.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class EventResponseDto implements Serializable {

    private Integer idx;

    private Timestamp begin_date;

    private Timestamp end_date;

    private String url;

    private String imgpath;

    @Builder
    public EventResponseDto(Integer idx, Timestamp begin_date, Timestamp end_date, String url, String imgpath) {
        this.idx = idx;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.url = url;
        this.imgpath = imgpath;
    }
}