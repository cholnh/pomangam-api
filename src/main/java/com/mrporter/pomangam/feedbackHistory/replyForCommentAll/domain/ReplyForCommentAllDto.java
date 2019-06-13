package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class ReplyForCommentAllDto implements Serializable {

    private Integer replyIdx;

    private String nickname;

    private String customer_id;

    private Byte state_anonymous;

    private Integer owner_idx;

    private Timestamp register_date;

    private String contents;

    private Byte likeType;

    private Integer cnt_like;

    private Integer cnt_unlike;

    @Builder
    public ReplyForCommentAllDto(Integer replyIdx, String nickname, String customer_id, Byte state_anonymous, Integer owner_idx, Timestamp register_date, String contents, Byte likeType, Integer cnt_like, Integer cnt_unlike) {
        this.replyIdx = replyIdx;
        this.nickname = nickname;
        this.customer_id = customer_id;
        this.state_anonymous = state_anonymous;
        this.owner_idx = owner_idx;
        this.register_date = register_date;
        this.contents = contents;
        this.likeType = likeType;
        this.cnt_like = cnt_like;
        this.cnt_unlike = cnt_unlike;
    }
}