package com.mrporter.pomangam.feedbackHistory.commentStore.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class CommentStoreDto implements Serializable {

    private Integer idx;

    private Integer store_idx;

    private Integer customer_idx;

    private Date register_date;

    private Date modify_date;

    private Byte cnt_star;

    private Integer cnt_like;

    private Integer cnt_unlike;

    private String contents;

    private String title;

    private Byte state_active;

    public CommentStore toEntity() {
        return null;
    }
}