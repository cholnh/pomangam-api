package com.mrporter.pomangam.promotionEntry.notice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class NoticeResponseDto implements Serializable {

    private Integer idx;

    private String title;

    private Timestamp begin_date;

    private Timestamp end_date;

    private String url;

    @Builder
    public NoticeResponseDto(Integer idx, String title, Timestamp begin_date, Timestamp end_date, String url) {
        this.idx = idx;
        this.title = title;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.url = url;
    }
}