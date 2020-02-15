package com.mrporter.pomangam.client.domains.store;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "store_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"storeCategory", "deliverySite"})
public class Store extends EntityAuditing {

    /**
     * 배달지
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_delivery_site")
    private DeliverySite deliverySite;

    /**
     * 업체명
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 업체 분류
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_store_category")
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

    @PrePersist
    private void prePersist() {
        // always 0 when its insert
        this.avgStar = 0f;
        this.cntLike = 0;
        this.cntComment = 0;
    }

    @Builder
    public Store(DeliverySite deliverySite, String name, StoreCategory storeCategory, String description, String subDescription, Float avgStar, Integer cntLike, Integer cntComment) {
        this.deliverySite = deliverySite;
        this.name = name;
        this.storeCategory = storeCategory;
        this.description = description;
        this.subDescription = subDescription;
        this.avgStar = avgStar;
        this.cntLike = cntLike;
        this.cntComment = cntComment;
    }
}
