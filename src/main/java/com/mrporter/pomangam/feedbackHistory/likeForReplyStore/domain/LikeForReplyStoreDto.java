package com.mrporter.pomangam.feedbackHistory.likeForReplyStore.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class LikeForReplyStoreDto implements Serializable {

    private Integer idx;

    private Integer reply_store_idx;

    private Integer customer_idx;

    private Byte type;

    public LikeForReplyStore toEntity() {
        return null;
    }
}