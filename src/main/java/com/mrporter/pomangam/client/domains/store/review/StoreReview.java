package com.mrporter.pomangam.client.domains.store.review;

import com.mrporter.pomangam._bases.annotation.BooleanToYNConverter;
import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.store.review.image.StoreReviewImage;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store_review_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class StoreReview extends EntityAuditing {

    /**
     * 유저 인덱스
     */
    @Column(name="idx_user", nullable = false)
    private Long idxUser;

    /**
     * 업체 인덱스
     */
    @Column(name="idx_store", nullable = false)
    private Long idxStore;

    /**
     * 익명 여부
     * default/null: true(Y)
     * 대문자 필수
     */
    @Column(name = "is_anonymous", nullable = false, length = 1)
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isAnonymous;

    /**
     * 리뷰 제목
     * 글자수: utf8 기준 / 영문 100자 / 한글 100자
     */
    @Column(name="title", nullable = false, length = 100)
    private String title;

    /**
     * 리뷰 내용
     * TEXT: 65535 Byte (64KB) / utf8 기준(3바이트 문자)으로 21844 글자 저장가능
     */
    @Column(name="contents", nullable = false, columnDefinition = "TEXT")
    private String contents;

    /**
     * 평점
     * 1~5점
     */
    @Column(name = "star", nullable = false, columnDefinition = "FLOAT default 0")
    private Float star;

    /**
     * 총 좋아요 개수
     */
    @Column(name = "cnt_like", nullable = false, columnDefinition = "INT default 0")
    private Integer cntLike;

    /**
     * 총 댓글 개수
     */
    @Column(name = "cnt_reply", nullable = false, columnDefinition = "INT default 0")
    private Integer cntReply;

    /**
     * 이미지 정보
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_store_review", nullable = false)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    private List<StoreReviewImage> images = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        this.star = star == null || star < 1 || star > 5
                ? 5f
                : star;
        this.cntLike = 0; // always 0 when its insert
        this.cntReply = 0;
    }

    @Builder
    public StoreReview(Long idxUser, Long idxStore, Boolean isAnonymous, String title, String contents, Float star, Integer cntLike, Integer cntReply, List<StoreReviewImage> images) {
        this.idxUser = idxUser;
        this.idxStore = idxStore;
        this.isAnonymous = isAnonymous;
        this.title = title;
        this.contents = contents;
        this.star = star;
        this.cntLike = cntLike;
        this.cntReply = cntReply;
        this.images = images;
    }

    public void clearImages() {
        if(this.images != null) {
            this.images.clear();
        }
    }
    public void addImages(List<StoreReviewImage> storeReviewImages) {
        if(this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.addAll(storeReviewImages);
    }
    public void addImage(StoreReviewImage storeReviewImage) {
        if(this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(storeReviewImage);
    }
    public void addCntLike() {
        if(this.cntLike == null) {
            this.cntLike = 0;
        }
        this.cntLike++;
    }
    public void addCntReply() {
        if(this.cntReply == null) {
            this.cntReply = 0;
        }
        this.cntReply++;
    }

    public void subCntLike() {
        if(this.cntLike == null) {
            this.cntLike = 0;
        }
        this.cntLike--;
        if(this.cntLike < 0) {
            this.cntLike = 0;
        }
    }
    public void subCntReply() {
        if(this.cntReply == null) {
            this.cntReply = 0;
        }
        this.cntReply--;
        if(this.cntReply < 0) {
            this.cntReply = 0;
        }
    }

    public StoreReview update(StoreReview from) {
        if(from.getIdxStore() != null) { this.setIdxStore(from.getIdxStore()); }
        if(from.getIsAnonymous() != null) { this.setIsAnonymous(from.getIsAnonymous()); }
        if(from.getTitle() != null) { this.setTitle(from.getTitle()); }
        if(from.getContents() != null) { this.setContents(from.getContents()); }
        if(from.getStar() != null) { this.setStar(from.getStar()); }
        return this;
    }
}

