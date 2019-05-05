package com.mrporter.pomangam.feedbackHistory.likeForStore.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LikeForStoreKey implements Serializable {
    private Integer store_idx;
    private Integer customer_idx;

    public LikeForStoreKey(Integer store_idx, Integer customer_idx) {
        this.store_idx = store_idx;
        this.customer_idx = customer_idx;
    }
}
