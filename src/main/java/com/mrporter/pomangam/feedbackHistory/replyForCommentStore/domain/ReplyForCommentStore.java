package com.mrporter.pomangam.feedbackHistory.replyForCommentStore.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "reply_for_comment_store_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class ReplyForCommentStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer comment_idx;

    private Integer customer_idx;

    private Integer owner_idx;

    private Date register_date;

    private Date modify_date;

    private String contents;

    private Byte state_active;

    @Builder
    public ReplyForCommentStore(Integer comment_idx, Integer customer_idx, Integer owner_idx, Date register_date, Date modify_date, String contents, Byte state_active) {
        this.comment_idx = comment_idx;
        this.customer_idx = customer_idx;
        this.owner_idx = owner_idx;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.contents = contents;
        this.state_active = state_active;
    }
}
