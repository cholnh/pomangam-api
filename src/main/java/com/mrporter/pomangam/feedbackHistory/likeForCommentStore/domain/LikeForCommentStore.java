package com.mrporter.pomangam.feedbackHistory.likeForCommentStore.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "like_for_comment_store_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class LikeForCommentStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer comment_store_idx;

    private Integer customer_idx;

    private Byte type;

    @Builder
    public LikeForCommentStore(Integer comment_store_idx, Integer customer_idx, Byte type) {
        this.comment_store_idx = comment_store_idx;
        this.customer_idx = customer_idx;
        this.type = type;
    }
}
