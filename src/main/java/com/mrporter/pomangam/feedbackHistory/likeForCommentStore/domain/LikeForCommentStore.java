package com.mrporter.pomangam.feedbackHistory.likeForCommentStore.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "like_for_comment_store_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@IdClass(LikeForCommentStoreKey.class)
public class LikeForCommentStore implements Serializable {

    @Id
    private Integer comment_store_idx;

    @Id
    private Integer customer_idx;

    private Byte type;

    @Builder
    public LikeForCommentStore(Integer comment_store_idx, Integer customer_idx, Byte type) {
        this.comment_store_idx = comment_store_idx;
        this.customer_idx = customer_idx;
        this.type = type;
    }
}
