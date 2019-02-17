package com.mrporter.pomangam.feedbackHistory.likeForReplyStore.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "like_for_reply_store_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class LikeForReplyStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer reply_store_idx;

    private Integer customer_idx;

    private Byte type;

    @Builder
    public LikeForReplyStore(Integer reply_store_idx, Integer customer_idx, Byte type) {
        this.reply_store_idx = reply_store_idx;
        this.customer_idx = customer_idx;
        this.type = type;
    }
}
