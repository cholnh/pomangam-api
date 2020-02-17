package com.mrporter.pomangam.client.domains.store;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import com.mrporter.pomangam.client.domains.store.image.StoreImage;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"storeCategory", "images"})
public class Store extends EntityAuditing {

    /**
     * 배달지 인덱스
     * cf. (업체와 배달지는 서로)에그리거트 단위를 벗어남 -> 불필요한 객체탐색이 포함됨 -> 연관관계 끊음
     */
    @Column(name = "idx_delivery_site", nullable = false)
    private Long idxDeliverySite;

    /**
     * 업체명
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    /**
     * 업체 분류
     */
    @JoinColumn(name = "idx_store_category")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StoreCategory storeCategory;

    /**
     * 설명
     * TEXT: 65535 Byte (64KB) / utf8 기준(3바이트 문자)으로 21844 글자 저장가능
     */
    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    /**
     * 부가 설명
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "sub_description", nullable = true, length = 255)
    private String subDescription;

    /**
     * 평균 리뷰 평점
     */
    @Column(name = "avg_star", nullable = false, columnDefinition = "FLOAT default 0")
    private Float avgStar;

    /**
     * 총 좋아요 개수
     */
    @Column(name = "cnt_like", nullable = false, columnDefinition = "INT default 0")
    private Integer cntLike;

    /**
     * 총 리뷰 개수
     */
    @Column(name = "cnt_comment", nullable = false, columnDefinition = "INT default 0")
    private Integer cntComment;

    /**
     * 순서
     */
    @Column(name = "sequence", nullable = false, columnDefinition = "INT default 0")
    private Integer sequence;

    /**
     * 이미지 정보
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_store", nullable = false)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    private List<StoreImage> images = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        // always 0 when its insert
        this.avgStar = 0f;
        this.cntLike = 0;
        this.cntComment = 0;
        this.sequence = sequence == null ? 0 : sequence;
    }

    @Builder
    public Store(Long idxDeliverySite, String name, StoreCategory storeCategory, String description, String subDescription, Float avgStar, Integer cntLike, Integer cntComment, Integer sequence, List<StoreImage> images) {
        this.idxDeliverySite = idxDeliverySite;
        this.name = name;
        this.storeCategory = storeCategory;
        this.description = description;
        this.subDescription = subDescription;
        this.avgStar = avgStar;
        this.cntLike = cntLike;
        this.cntComment = cntComment;
        this.sequence = sequence;
        this.images = images;
    }

    public void addImage(StoreImage storeImage) {
        if(this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(storeImage);
    }
    public void addCntLike() {
        if(this.cntLike == null) {
            this.cntLike = 0;
        }
        this.cntLike++;
    }
    public void addCntComment(Float star) {
        if(this.cntComment == null) {
            this.cntComment = 0;
        }
        if(this.avgStar == null) {
            this.avgStar = 0f;
        }
        this.avgStar = ((avgStar * cntComment) + star) / (++this.cntComment);
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
    public void subCntComment(Float star) {
        if(this.cntComment == null || this.cntComment <= 0) {
            this.cntComment = 0;
            this.avgStar = 0f;
        } else if(this.avgStar == null || this.avgStar <= 0) {
            this.avgStar = 0f;
        } else {
            this.avgStar = ((avgStar * cntComment) - star) / (--this.cntComment);
        }
    }

}
