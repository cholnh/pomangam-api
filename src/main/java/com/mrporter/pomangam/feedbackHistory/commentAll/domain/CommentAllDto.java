package com.mrporter.pomangam.feedbackHistory.commentAll.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class CommentAllDto implements Serializable {

    private Integer idx;

    private String store_name;

    private String title;

    private Integer cnt_like;

    private Integer cnt_unlike;

    private Integer cnt_view;

    private Timestamp register_date;

    @Builder
    public CommentAllDto(Integer idx, String store_name, String title, Integer cnt_like, Integer cnt_unlike, Integer cnt_view, Timestamp register_date) {
        this.idx = idx;
        this.store_name = store_name;
        this.title = title;
        this.cnt_like = cnt_like;
        this.cnt_unlike = cnt_unlike;
        this.cnt_view = cnt_view;
        this.register_date = register_date;
    }
}