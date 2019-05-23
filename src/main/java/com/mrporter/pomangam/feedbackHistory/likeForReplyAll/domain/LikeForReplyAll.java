package com.mrporter.pomangam.feedbackHistory.likeForReplyAll.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "like_for_reply_all_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@IdClass(LikeForReplyAllKey.class)
public class LikeForReplyAll implements Serializable {

    @Id
    private Integer reply_all_idx;

    @Id
    private Integer customer_idx;

    private Byte type;


    @Builder
    public LikeForReplyAll(Integer reply_all_idx, Integer customer_idx, Byte type) {
        this.reply_all_idx = reply_all_idx;
        this.customer_idx = customer_idx;
        this.type = type;
    }
}
