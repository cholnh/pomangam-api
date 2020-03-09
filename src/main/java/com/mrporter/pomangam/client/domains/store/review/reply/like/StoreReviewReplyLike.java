package com.mrporter.pomangam.client.domains.store.review.reply.like;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "store_review_reply_like_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class StoreReviewReplyLike extends EntityAuditing {

    /**
     * 유저 인덱스
     */
    @Column(name="idx_user", nullable = false)
    private Long idxUser;

    /**
     * 제품 댓글 인덱스
     */
    @Column(name="idx_store_review_reply", nullable = false)
    private Long idxStoreReviewReply;

    @Builder
    public StoreReviewReplyLike(Long idxUser, Long idxStoreReviewReply) {
        this.idxUser = idxUser;
        this.idxStoreReviewReply = idxStoreReviewReply;
    }
}

