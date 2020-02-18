package com.mrporter.pomangam.client.domains.store.review.reply;

import com.mrporter.pomangam._bases.annotation.BooleanToYNConverter;
import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "store_review_reply_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class StoreReviewReply extends EntityAuditing {

    /**
     * 유저 인덱스
     */
    @Column(name="idx_user", nullable = false)
    private Long idxUser;

    /**
     * 업체 리뷰 인덱스
     */
    @Column(name="idx_store_review", nullable = false)
    private Long idxStoreReview;

    /**
     * 익명 여부
     * default/null: true(Y)
     * 대문자 필수
     */
    @Column(name = "is_anonymous", nullable = false, length = 1)
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isAnonymous;

    /**
     * 리뷰 내용
     * 글자수: utf8 기준 / 영문 300자 / 한글 300자
     */
    @Column(name="contents", nullable = false, length = 300)
    private String contents;

    @Builder
    public StoreReviewReply(Long idxUser, Long idxStoreReview, Boolean isAnonymous, String contents) {
        this.idxUser = idxUser;
        this.idxStoreReview = idxStoreReview;
        this.isAnonymous = isAnonymous;
        this.contents = contents;
    }

    public StoreReviewReply update(StoreReviewReply from) {
        if(from.getIdxStoreReview() != null) { this.setIdxStoreReview(from.getIdxStoreReview()); }
        if(from.getIsAnonymous() != null) { this.setIsAnonymous(from.getIsAnonymous()); }
        if(from.getContents() != null) { this.setContents(from.getContents()); }
        return this;
    }
}

