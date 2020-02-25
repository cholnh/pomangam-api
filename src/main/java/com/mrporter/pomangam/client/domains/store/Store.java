package com.mrporter.pomangam.client.domains.store;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import com.mrporter.pomangam.client.domains.store.image.StoreImage;
import com.mrporter.pomangam.client.domains.store.info.ProductionInfo;
import com.mrporter.pomangam.client.domains.store.info.StoreInfo;
import com.mrporter.pomangam.client.domains.store.schedule.StoreSchedule;
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
     * 업체 분류
     */
    @JoinColumn(name = "idx_store_category")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StoreCategory storeCategory;

    /**
     * 업체 정보
     */
    @Embedded
    private StoreInfo storeInfo;

    /**
     * 업체 생산량
     */
    @Embedded
    ProductionInfo productionInfo;

    /**
     * 업체 영업 시간
     */
    @Embedded
    StoreSchedule storeSchedule;

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
    public Store(Long idx, Long idxDeliverySite, StoreCategory storeCategory, StoreInfo storeInfo, ProductionInfo productionInfo, StoreSchedule storeSchedule, Float avgStar, Integer cntLike, Integer cntComment, Integer sequence, List<StoreImage> images) {
        super.setIdx(idx);
        this.idxDeliverySite = idxDeliverySite;
        this.storeCategory = storeCategory;
        this.storeInfo = storeInfo;
        this.productionInfo = productionInfo;
        this.storeSchedule = storeSchedule;
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
        if(this.cntComment == null || this.cntComment <= 1) {
            this.cntComment = 0;
            this.avgStar = 0f;
        } else if(this.avgStar == null || this.avgStar <= 0) {
            this.avgStar = 0f;
        } else {
            this.avgStar = ((avgStar * cntComment) - star) / (--this.cntComment);
        }
    }

}
