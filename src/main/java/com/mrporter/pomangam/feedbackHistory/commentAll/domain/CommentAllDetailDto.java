package com.mrporter.pomangam.feedbackHistory.commentAll.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class CommentAllDetailDto implements Serializable {

    private Integer idx;

    private String store_name;

    private String title;

    private String contents;

    private Integer cnt_like;

    private Integer cnt_unlike;

    private Integer cnt_view;

    private Timestamp register_date;

    private String nickname;

    private String customer_id;

    private Byte state_anonymous;

    private Byte likeType;

    @Builder
    public CommentAllDetailDto(Integer idx, String store_name, String title, String contents, Integer cnt_like, Integer cnt_unlike, Integer cnt_view, Timestamp register_date, String nickname, String customer_id, Byte state_anonymous, Byte likeType) {
        this.idx = idx;
        this.store_name = store_name;
        this.title = title;
        this.contents = contents;
        this.cnt_like = cnt_like;
        this.cnt_unlike = cnt_unlike;
        this.cnt_view = cnt_view;
        this.register_date = register_date;
        this.nickname = nickname;
        this.customer_id = customer_id;
        this.state_anonymous = state_anonymous;
        this.likeType = likeType;
    }
}