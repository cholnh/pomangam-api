package com.mrporter.pomangam.feedbackHistory.replyForCommentStore.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ReplyForCommentStoreDto implements Serializable {

    private Integer idx;

    private Integer comment_idx;

    private Integer customer_idx;

    private Integer owner_idx;

    private Date register_date;

    private Date modify_date;

    private String contents;

    private Byte state_active;

    public ReplyForCommentStore toEntity() {
        return null;
    }
}