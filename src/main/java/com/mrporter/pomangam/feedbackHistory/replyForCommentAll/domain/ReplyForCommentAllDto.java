package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ReplyForCommentAllDto implements Serializable {

    private Integer idx;

    private Integer comment_all_idx;

    private Integer customer_idx;

    private Integer owner_idx;

    private Timestamp register_date;

    private Timestamp modify_date;

    private String contents;

    private Byte state_active;

    private Byte state_anonymous;

    public ReplyForCommentAll toEntity() {
        return null;
    }
}