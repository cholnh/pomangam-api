package com.mrporter.pomangam.feedbackHistory.likeForProduct.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "like_for_product_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@IdClass(LikeForProductKey.class)
public class LikeForProduct implements Serializable {

    @Id
    private Integer product_idx;

    @Id
    private Integer customer_idx;

    private Byte type;

    @Builder
    public LikeForProduct(Integer product_idx, Integer customer_idx, Byte type) {
        this.product_idx = product_idx;
        this.customer_idx = customer_idx;
        this.type = type;
    }
}
