package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "reply_for_comment_all_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class ReplyForCommentAll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer comment_all_idx;

    private Integer customer_idx;

    private Integer owner_idx;

    private Timestamp register_date;

    private Timestamp modify_date;

    private String contents;

    private Byte state_active;

    private Byte state_anonymous;

    @Builder
    public ReplyForCommentAll(Integer comment_all_idx, Integer customer_idx, Integer owner_idx, Timestamp register_date, Timestamp modify_date, String contents, Byte state_active, Byte state_anonymous) {
        this.comment_all_idx = comment_all_idx;
        this.customer_idx = customer_idx;
        this.owner_idx = owner_idx;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.contents = contents;
        this.state_active = state_active;
        this.state_anonymous = state_anonymous;
    }
}
