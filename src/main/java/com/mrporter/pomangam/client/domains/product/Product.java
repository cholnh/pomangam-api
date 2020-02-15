package com.mrporter.pomangam.client.domains.product;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.product.category.ProductCategory;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "product_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Product extends EntityAuditing {

    /**
     * 업체 인덱스
     * cf. (업체와 제품은 서로)에그리거트 단위를 벗어남 -> 불필요한 객체탐색이 포함됨 -> 연관관계 끊음
     */
    @Column(name = "idx_store", nullable = false)
    private Long idxStore;

    /**
     * 제품명
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 제품명
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * 제품명
     */
    @Column(name = "sub_description", nullable = false)
    private String subDescription;

    /**
     * 제품 분류
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_product_category")
    private ProductCategory productCategory;

    /**
     * Product Type
     */
    @Column(name = "product_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    /**
     * 총 좋아요 개수
     */
    @Column(name = "cnt_like", nullable = true)
    private Integer cntLike;

    /**
     * 순서
     */
    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @PrePersist
    private void prePersist() {
        this.cntLike = 0; // always 0 when its insert
        this.sequence = sequence == null ? 0 : sequence;
    }
}
