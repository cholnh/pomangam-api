package com.mrporter.pomangam.client.domains.promotion;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.order.Order;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * promotion - order [N:M] 연관관계 설정을 위한 양방향 Mapper
 */
@Entity
@Table(name = "promotion_mapper_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PromotionMapper extends EntityAuditing {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_promotion", nullable = false)
    private Promotion promotion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_order", nullable = false)
    private Order order;

    @Builder
    public PromotionMapper(Promotion promotion, Order order) {
        this.promotion = promotion;
        this.order = order;
    }
}
