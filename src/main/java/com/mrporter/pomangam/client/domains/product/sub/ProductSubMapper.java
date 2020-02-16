package com.mrporter.pomangam.client.domains.product.sub;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.product.Product;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * product - sub [N:M] 연관관계 설정을 위한 양방향 Mapper
 */
@Entity
@Table(name = "product_sub_mapper_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ProductSubMapper extends EntityAuditing {

    @ManyToOne
    @JoinColumn(name = "idx_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idx_product_sub")
    private ProductSub productSub;

    @Builder
    public ProductSubMapper(Product product, ProductSub productSub) {
        this.product = product;
        this.productSub = productSub;
    }
}

