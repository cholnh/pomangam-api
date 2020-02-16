package com.mrporter.pomangam.client.domains.product;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.product.category.ProductCategory;
import com.mrporter.pomangam.client.domains.product.cost.Cost;
import com.mrporter.pomangam.client.domains.product.image.ProductImage;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubMapper;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"productCategory", "images", "products"})
public class Product extends EntityAuditing {

    /**
     * 업체 인덱스
     * cf. (업체와 제품은 서로)에그리거트 단위를 벗어남 -> 불필요한 객체탐색이 포함됨 -> 연관관계 끊음
     */
    @Column(name = "idx_store", nullable = false)
    private Long idxStore;

    /**
     * 가격
     */
    @Embedded
    private Cost cost;

    /**
     * 제품명
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 제품 설명
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * 제품 부가 설명
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
     * 총 좋아요 개수
     */
    @Column(name = "cnt_like", nullable = true)
    private Integer cntLike;

    /**
     * 순서
     */
    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    /**
     * 이미지 정보
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_product", nullable = false)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    private List<ProductImage> images = new ArrayList<>();

    /**
     * 서브 제품 Mapper
     */
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @OrderBy("sequence ASC")
    private List<ProductSubMapper> subs = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        this.cntLike = 0; // always 0 when its insert
        this.sequence = sequence == null ? 0 : sequence;
    }

    @Builder
    public Product(Long idxStore, Cost cost, String name, String description, String subDescription, ProductCategory productCategory, Integer cntLike, Integer sequence, List<ProductImage> images, List<ProductSubMapper> subs) {
        this.idxStore = idxStore;
        this.cost = cost;
        this.name = name;
        this.description = description;
        this.subDescription = subDescription;
        this.productCategory = productCategory;
        this.cntLike = cntLike;
        this.sequence = sequence;
        this.images = images;
        this.subs = subs;
    }

    public void addImage(ProductImage productImage) {
        if(this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(productImage);
    }
}
