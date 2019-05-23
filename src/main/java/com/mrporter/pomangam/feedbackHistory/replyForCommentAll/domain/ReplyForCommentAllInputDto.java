package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class ReplyForCommentAllInputDto implements Serializable {

    private Integer commentAllIdx;

    private Integer customer_idx;

    private Boolean isAnonymous;

    private Integer owner_idx;

    private String contents;

    @Builder
    public ReplyForCommentAllInputDto(Integer commentAllIdx, Integer customer_idx, Boolean isAnonymous, Integer owner_idx, String contents) {
        this.commentAllIdx = commentAllIdx;
        this.customer_idx = customer_idx;
        this.isAnonymous = isAnonymous;
        this.owner_idx = owner_idx;
        this.contents = contents;
    }
}