package com.mrporter.pomangam.feedbackHistory.commentStore.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "comment_for_store_tbl")
@NoArgsConstructor
@Data
@Entity
public class CommentStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer store_idx;

    private Integer customer_idx;

    private Float cnt_star;

    private Integer cnt_like;

    private String contents;

    private Timestamp register_date;

    private Timestamp modify_date;

    private Byte state_active;

    private Byte state_anonymous;

    @Builder
    public CommentStore(Integer store_idx, Integer customer_idx, Float cnt_star, Integer cnt_like, String contents, Timestamp register_date, Timestamp modify_date, Byte state_active, Byte state_anonymous) {
        this.store_idx = store_idx;
        this.customer_idx = customer_idx;
        this.cnt_star = cnt_star;
        this.cnt_like = cnt_like;
        this.contents = contents;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.state_active = state_active;
        this.state_anonymous = state_anonymous;
    }
}
