package com.mrporter.pomangam.client.domains.store.review.like;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "store_review_like_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class StoreReviewLike extends EntityAuditing {

    /**
     * 유저 인덱스
     */
    @Column(name="idx_user", nullable = false)
    private Long idxUser;

    /**
     * 업체 인덱스
     */
    @Column(name="idx_store_review", nullable = false)
    private Long idxStoreReview;

    @Builder
    public StoreReviewLike(Long idxUser, Long idxStoreReview) {
        this.idxUser = idxUser;
        this.idxStoreReview = idxStoreReview;
    }
}

