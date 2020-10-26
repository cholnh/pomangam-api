package com.mrporter.pomangam.client.domains.order.item;

import com.mrporter.pomangam._bases.annotation.BooleanToYNConverter;
import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.order.item.sub.OrderItemSub;
import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.store.Store;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_item_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class OrderItem extends EntityAuditing {

    /**
     * 업체
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_store")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Store store;

    /**
     * 제품
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_product")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;

    /**
     * 주문 수량
     */
    @Column(name = "quantity", nullable = false)
    private Short quantity;

    /**
     * 요구사항
     * 글자수: utf8 기준 / 영문 100자 / 한글 100자
     */
    @Column(name = "requirement", nullable = true, length = 100)
    private String requirement;

    /**
     * 서브 제품 리스트
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_order_item", nullable = false)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("idx ASC")
    List<OrderItemSub> orderItemSubs = new ArrayList<>();

    /**
     * 리뷰 작성 여부 (Y/N)
     * default: false(N)
     * 대문자 필수
     */
    @Column(name = "is_review_write", nullable = false, length = 1)
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isReviewWrite;

    @PrePersist
    private void prePersist() {
        this.isReviewWrite = false;
    }


    public int paymentCost() {
        int itemsTotalCost = 0;

        // 제품 가격 합
        if(this.product != null) {
            int salePrice = this.product.getCost().saleCost();
            itemsTotalCost += salePrice * this.quantity.intValue();
        }

        // 서브 제품 가격 합
        if(this.orderItemSubs != null) {
            for(OrderItemSub orderItemSub : this.orderItemSubs) {
                int salePrice = orderItemSub.getProductSub().getCost().saleCost();
                itemsTotalCost += salePrice * orderItemSub.getQuantity().intValue();
            }
        }
        return itemsTotalCost;
    }

    @Builder
    public OrderItem(Store store, Product product, Short quantity, String requirement, List<OrderItemSub> orderItemSubs, boolean isReviewWrite) {
        this.store = store;
        this.product = product;
        this.quantity = quantity;
        this.requirement = requirement;
        this.orderItemSubs = orderItemSubs;
        this.isReviewWrite = isReviewWrite;
    }

    public void addItem(OrderItemSub orderItemSub) {
        if(this.orderItemSubs == null) {
            this.orderItemSubs = new ArrayList<>();
        }
        if(orderItemSub != null) {
            this.orderItemSubs.add(orderItemSub);
        }
    }
}

