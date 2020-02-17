package com.mrporter.pomangam.client.domains.product.sub;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.product.cost.Cost;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategory;
import com.mrporter.pomangam.client.domains.product.sub.image.ProductSubImage;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_sub_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"productSubCategory", "images", "products"})
public class ProductSub extends EntityAuditing {

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
     * 서브 제품명
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 서브 제품 설명
     */
    @Column(name = "description", nullable = true)
    private String description;

    /**
     * 서브 제품 부가설명
     */
    @Column(name = "sub_description", nullable = true)
    private String subDescription;

    /**
     * 순서
     */
    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    /**
     * 이미지 정보
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_product_sub", nullable = false)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    private List<ProductSubImage> images = new ArrayList<>();

    /**
     * 서브 제품 종류
     * 다중선택: ProductSubType.CHECKBOX
     * 단일선택(필수선택강요): ProductSubType.RADIO
     * 수량기입: ProductSubType.NUMBER
     * 변경불가(선택된상태): ProductSubType.READONLY -> 기본으로 제공되는 서브제품을 가시적으로 표현할 때 사용.
     */
    @Column(name = "product_sub_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductSubType productSubType;

    /**
     * 서브 제품 분류
     * ProductSubType.RADIO 선택 시,
     * ProductSubCategory 가 같은 ProductSub 끼리 Grouping 하여 View 에 표현.
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_product_sub_category")
    private ProductSubCategory productSubCategory;

    /**
     * ProductSubType.NUMBER 선택 시,
     * 선택 최소 치
     * null -> 하한 없음
     */
    @Column(name = "number_minimum", nullable = true)
    private Integer numberMinimum;

    /**
     * ProductSubType.NUMBER 선택 시,
     * 선택 최대 치
     * null -> 상한 없음
     */
    @Column(name = "number_maximum", nullable = true)
    private Integer numberMaximum;

    /**
     * 제품 Mapper
     */
    @OneToMany(mappedBy = "productSub", fetch = FetchType.LAZY)
    private List<ProductSubMapper> products = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        this.numberMinimum = numberMinimum == null
                ? null
                : numberMinimum < 0 ? null : numberMaximum;
        this.numberMaximum = numberMaximum == null
                ? null
                : numberMaximum < 0 ? null : numberMaximum;
        this.sequence = sequence == null ? 0 : sequence;
    }

    @Builder
    public ProductSub(Long idxStore, Cost cost, String name, String description, String subDescription, Integer sequence, List<ProductSubImage> images, ProductSubType productSubType, ProductSubCategory productSubCategory, Integer numberMinimum, Integer numberMaximum, List<ProductSubMapper> products) {
        this.idxStore = idxStore;
        this.cost = cost;
        this.name = name;
        this.description = description;
        this.subDescription = subDescription;
        this.sequence = sequence;
        this.images = images;
        this.productSubType = productSubType;
        this.productSubCategory = productSubCategory;
        this.numberMinimum = numberMinimum;
        this.numberMaximum = numberMaximum;
        this.products = products;
    }

    public void addImage(ProductSubImage productSubImage) {
        if(this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(productSubImage);
    }
}