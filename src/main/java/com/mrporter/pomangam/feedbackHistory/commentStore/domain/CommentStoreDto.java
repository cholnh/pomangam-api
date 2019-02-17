package com.mrporter.pomangam.feedbackHistory.commentStore.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CommentStoreDto implements Serializable {

    private Integer idx;

    private Integer store_idx;

    private Integer customer_idx;

    private Byte cnt_star;

    private Integer cnt_like;

    private String contents;

    private Timestamp register_date;

    private Timestamp modify_date;

    private Byte state_active;

    private Byte state_anonymous;

    public CommentStore toEntity() {
        return null;
    }
}