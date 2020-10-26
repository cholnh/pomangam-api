package com.mrporter.pomangam.client.domains.order.item.sub;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "order_item_sub_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class OrderItemSub extends EntityAuditing {

    /**
     * 서브 제품
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_product_sub")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ProductSub productSub;

    /**
     * 주문 수량
     */
    @Column(name = "quantity", nullable = false)
    private Short quantity;

    @Builder
    public OrderItemSub(ProductSub productSub, Short quantity) {
        this.productSub = productSub;
        this.quantity = quantity;
    }
}

