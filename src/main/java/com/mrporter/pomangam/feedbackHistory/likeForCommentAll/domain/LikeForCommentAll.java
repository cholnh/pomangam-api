package com.mrporter.pomangam.feedbackHistory.likeForCommentAll.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "like_for_comment_all_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class LikeForCommentAll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer comment_all_idx;

    private Integer customer_idx;

    private Byte type;

    @Builder
    public LikeForCommentAll(Integer comment_all_idx, Integer customer_idx, Byte type) {
        this.comment_all_idx = comment_all_idx;
        this.customer_idx = customer_idx;
        this.type = type;
    }
}
