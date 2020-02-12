package com.mrporter.pomangam.client.domains.store;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Table(name = "store_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString(exclude = {"storeCategory", "deliverySite"})
@Entity
@DynamicUpdate
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    /**
     * 배달지
     */
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_delivery_site")
    private DeliverySite deliverySite;

    /**
     * 업체명
     */
    private String title;

    /**
     * 업체 분류 명
     * optional : 해당 객체 nullable true/false
     */
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_store_category")
    private StoreCategory storeCategory;

    /**
     * 설명
     */
    private String description;

    /**
     * 부가 설명
     */
    private String subDescription;

    /**
     * 평균 리뷰 평점
     */
    @ColumnDefault("0")
    @Column(name = "avg_star")
    private Float avgStar;

    /**
     * 총 좋아요 개수
     */
    @ColumnDefault("0")
    @Column(name = "cnt_like")
    private Integer cntLike;

    /**
     * 총 리뷰 개수
     */
    @ColumnDefault("0")
    @Column(name = "cnt_comment")
    private Integer cntComment;

    @Builder
    public Store(DeliverySite deliverySite, String title, StoreCategory storeCategory, String description, String subDescription, Float avgStar, Integer cntLike, Integer cntComment) {
        this.deliverySite = deliverySite;
        this.title = title;
        this.storeCategory = storeCategory;
        this.description = description;
        this.subDescription = subDescription;
        this.avgStar = avgStar;
        this.cntLike = cntLike;
        this.cntComment = cntComment;
    }
}
