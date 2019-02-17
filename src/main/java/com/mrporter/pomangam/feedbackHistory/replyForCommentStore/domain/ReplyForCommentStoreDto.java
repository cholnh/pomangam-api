package com.mrporter.pomangam.feedbackHistory.replyForCommentStore.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ReplyForCommentStoreDto implements Serializable {

    private Integer idx;

    private Integer comment_store_idx;

    private Integer customer_idx;

    private Integer owner_idx;

    private Timestamp register_date;

    private Timestamp modify_date;

    private String contents;

    private Byte state_active;

    private Byte state_anonymous;

    public ReplyForCommentStore toEntity() {
        return null;
    }
}