package com.mrporter.pomangam.feedbackHistory.likeForReplyAll.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LikeForReplyAllKey implements Serializable {
    private Integer reply_all_idx;
    private Integer customer_idx;

    public LikeForReplyAllKey(Integer reply_all_idx, Integer customer_idx) {
        this.reply_all_idx = reply_all_idx;
        this.customer_idx = customer_idx;
    }
}
