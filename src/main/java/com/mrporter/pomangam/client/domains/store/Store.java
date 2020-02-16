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
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 업체 분류
     */
    @JoinColumn(name = "idx_store_category")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StoreCategory storeCategory;

    /**
     * 설명
     */
    @Column(name = "description", nullable = true)
    private String description;

    /**
     * 부가 설명
     */
    @Column(name = "sub_description", nullable = true)
    private String subDescription;

    /**
     * 평균 리뷰 평점
     */
    @Column(name = "avg_star", nullable = true)
    private Float avgStar;

    /**
     * 총 좋아요 개수
     */
    @Column(name = "cnt_like", nullable = true)
    private Integer cntLike;

    /**
     * 총 리뷰 개수
     */
    @Column(name = "cnt_comment", nullable = true)
    private Integer cntComment;

    /**
     * 순서
     */
    @Column(name = "sequence", nullable = false)
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
}
