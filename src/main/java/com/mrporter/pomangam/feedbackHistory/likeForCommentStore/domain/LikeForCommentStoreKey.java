package com.mrporter.pomangam.feedbackHistory.likeForCommentStore.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LikeForCommentStoreKey implements Serializable {
    private Integer comment_store_idx;
    private Integer customer_idx;

    public LikeForCommentStoreKey(Integer comment_store_idx, Integer customer_idx) {
        this.comment_store_idx = comment_store_idx;
        this.customer_idx = customer_idx;
    }
}
