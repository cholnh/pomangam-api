package com.mrporter.pomangam.feedbackHistory.likeForCommentStore.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class LikeForCommentStoreDto implements Serializable {

    private Integer idx;

    private Integer comment_store_idx;

    private Integer customer_idx;

    private Byte type;

    public LikeForCommentStore toEntity() {
        return null;
    }
}