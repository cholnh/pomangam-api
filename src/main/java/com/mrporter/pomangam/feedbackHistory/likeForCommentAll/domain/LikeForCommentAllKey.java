package com.mrporter.pomangam.feedbackHistory.likeForCommentAll.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LikeForCommentAllKey implements Serializable {
    private Integer comment_all_idx;
    private Integer customer_idx;

    public LikeForCommentAllKey(Integer comment_all_idx, Integer customer_idx) {
        this.comment_all_idx = comment_all_idx;
        this.customer_idx = customer_idx;
    }
}
