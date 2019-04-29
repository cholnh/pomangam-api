package com.mrporter.pomangam.feedbackHistory.commentAll.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "comment_for_all_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class CommentAll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public CommentAll(Integer store_idx, Integer customer_idx, Timestamp register_date, Timestamp modify_date, Integer cnt_like, Integer cnt_unlike, Integer cnt_view, String title, String contents, Byte state_active, Byte state_anonymous) {
        this.store_idx = store_idx;
        this.customer_idx = customer_idx;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.cnt_like = cnt_like;
        this.cnt_unlike = cnt_unlike;
        this.cnt_view = cnt_view;
        this.title = title;
        this.contents = contents;
        this.state_active = state_active;
        this.state_anonymous = state_anonymous;
    }
}
