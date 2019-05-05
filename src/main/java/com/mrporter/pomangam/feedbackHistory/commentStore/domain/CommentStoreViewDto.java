package com.mrporter.pomangam.feedbackHistory.commentStore.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class CommentStoreViewDto implements Serializable {

    private Integer idx;

    private Integer store_idx;

    private String customer_nickname;

    private Float cnt_star;

    private Integer cnt_like;

    private String contents;

    private Timestamp register_date;

    private Byte state_anonymous;

    private Byte likeType;

    @Builder
    public CommentStoreViewDto(Integer idx, Integer store_idx, String customer_nickname, Float cnt_star, Integer cnt_like, String contents, Timestamp register_date, Byte state_anonymous, Byte likeType) {
        this.idx = idx;
        this.store_idx = store_idx;
        this.customer_nickname = customer_nickname;
        this.cnt_star = cnt_star;
        this.cnt_like = cnt_like;
        this.contents = contents;
        this.register_date = register_date;
        this.state_anonymous = state_anonymous;
        this.likeType = likeType;
    }
}