package com.mrporter.pomangam.feedbackHistory.likeForProduct.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LikeForProductKey implements Serializable {
    private Integer product_idx;
    private Integer customer_idx;

    public LikeForProductKey(Integer product_idx, Integer customer_idx) {
        this.product_idx = product_idx;
        this.customer_idx = customer_idx;
    }
}
