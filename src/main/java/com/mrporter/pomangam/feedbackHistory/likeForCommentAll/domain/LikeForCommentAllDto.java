package com.mrporter.pomangam.feedbackHistory.likeForCommentAll.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class LikeForCommentAllDto implements Serializable {

    private Integer idx;

    private Integer comment_all_idx;

    private Integer customer_idx;

    private Byte type;

    public LikeForCommentAll toEntity() {
        return null;
    }
}