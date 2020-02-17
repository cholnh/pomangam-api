package com.mrporter.pomangam.client.domains.product.category;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.product.Product;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_category_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ProductCategory extends EntityAuditing {

    /**
     * 제품 카테고리 타이틀
     * ex) 메인, 서브, 음료 등
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "category_title", nullable = false, length = 20)
    private String categoryTitle;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
    @OrderBy("sequence ASC")
    private List<Product> products;

    @Builder
    public ProductCategory(String categoryTitle, List<Product> products) {
        this.categoryTitle = categoryTitle;
        if(products != null) {
            this.products = products;
        }
    }
}
