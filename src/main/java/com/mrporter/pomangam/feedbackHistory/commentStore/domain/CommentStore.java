package com.mrporter.pomangam.feedbackHistory.commentStore.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "comment_for_all_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class CommentStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer store_idx;

    private Integer customer_idx;

    private Date register_date;

    private Date modify_date;

    private Byte cnt_star;

    private Integer cnt_like;

    private Integer cnt_unlike;

    private String contents;

    private String title;

    private Byte state_active;

    @Builder
    public CommentStore(Integer store_idx, Integer customer_idx, Date register_date, Date modify_date, Byte cnt_star, Integer cnt_like, Integer cnt_unlike, String contents, String title, Byte state_active) {
        this.store_idx = store_idx;
        this.customer_idx = customer_idx;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.cnt_star = cnt_star;
        this.cnt_like = cnt_like;
        this.cnt_unlike = cnt_unlike;
        this.contents = contents;
        this.title = title;
        this.state_active = state_active;
    }
}
