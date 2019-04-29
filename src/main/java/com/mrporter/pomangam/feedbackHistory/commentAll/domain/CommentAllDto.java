package com.mrporter.pomangam.feedbackHistory.commentAll.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CommentAllDto implements Serializable {

    private Integer idx;

    private Integer store_idx;

    private Integer customer_idx;

    private Timestamp register_date;

    private Timestamp modify_date;

    private Integer cnt_like;

    private Integer cnt_unlike;

    private Integer cnt_view;

    private String title;

    private String contents;

    private Byte state_active;

    private Byte state_anonymous;

    public CommentAll toEntity() {
        return null;
    }
}