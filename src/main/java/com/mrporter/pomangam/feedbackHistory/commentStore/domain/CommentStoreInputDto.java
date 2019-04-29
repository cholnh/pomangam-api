package com.mrporter.pomangam.feedbackHistory.commentStore.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommentStoreInputDto {
    private Integer store_idx;

    private Integer customer_idx;

    private Float cnt_star;

    private String contents;

    private Byte state_anonymous;

    public CommentStoreInputDto(Integer store_idx, Integer customer_idx, Float cnt_star, String contents, Byte state_anonymous) {
        this.store_idx = store_idx;
        this.customer_idx = customer_idx;
        this.cnt_star = cnt_star;
        this.contents = contents;
        this.state_anonymous = state_anonymous;
    }
}
