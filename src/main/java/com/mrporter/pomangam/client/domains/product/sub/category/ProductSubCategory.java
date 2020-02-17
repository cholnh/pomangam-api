package com.mrporter.pomangam.client.domains.product.sub.category;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
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

    @OneToMany(mappedBy = "productSubCategory", fetch = FetchType.LAZY)
    @OrderBy("sequence ASC")
    private List<ProductSub> productSubs;

    @Builder
    public ProductSubCategory(String categoryTitle, List<ProductSub> productSubs) {
        this.categoryTitle = categoryTitle;
        if(productSubs != null) {
            this.productSubs = productSubs;
        }
    }
}
