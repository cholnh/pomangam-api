package com.mrporter.pomangam.feedbackHistory.commentAll.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CommentAllInputDto implements Serializable {

    private Integer deliverySiteIdx;

    private Integer storeIdx;

    private String title;

    private Boolean isAnonymous;

    private Integer customerIdx;

    private String contents;

    @Builder
    public CommentAllInputDto(Integer deliverySiteIdx, Integer storeIdx, String title, Boolean isAnonymous, Integer customerIdx, String contents) {
        this.deliverySiteIdx = deliverySiteIdx;
        this.storeIdx = storeIdx;
        this.title = title;
        this.isAnonymous = isAnonymous;
        this.customerIdx = customerIdx;
        this.contents = contents;
    }
}