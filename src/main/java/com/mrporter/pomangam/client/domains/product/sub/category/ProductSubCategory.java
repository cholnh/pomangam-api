package com.mrporter.pomangam.client.domains.product.sub.category;

import com.mrporter.pomangam._bases.annotation.BooleanToYNConverter;
import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_sub_category_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ProductSubCategory extends EntityAuditing {

    /**
     * 서브 제품 카테고리 타이틀
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "category_title", nullable = false, length = 20)
    private String categoryTitle;

    @Column(name = "is_necessary", nullable = false, length = 1)
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isNecessary;

    /**
     * 서브 제품 종류
     * 다중선택: ProductSubType.CHECKBOX
     * 단일선택(필수선택강요): ProductSubType.RADIO
     * 수량기입: ProductSubType.NUMBER
     * 변경불가(선택된상태): ProductSubType.READONLY -> 기본으로 제공되는 서브제품을 가시적으로 표현할 때 사용.
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "product_sub_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ProductSubType productSubType;

    @OneToMany(mappedBy = "productSubCategory", fetch = FetchType.LAZY)
    @OrderBy("sequence ASC")
    private List<ProductSub> productSubs;

    @Builder
    public ProductSubCategory(Long idx, String categoryTitle, Boolean isNecessary, ProductSubType productSubType, List<ProductSub> productSubs) {
        super.setIdx(idx);
        this.categoryTitle = categoryTitle;
        this.isNecessary = isNecessary;
        this.productSubType = productSubType;
        this.productSubs = productSubs;
    }
}
