package com.mrporter.pomangam.feedbackHistory.likeForReplyAll.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class LikeForReplyAllDto implements Serializable {

    private Integer idx;

    private Integer reply_all_idx;

    private Integer customer_idx;

    private Byte type;

    public LikeForReplyAll toEntity() {
        return null;
    }
}