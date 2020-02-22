package com.mrporter.pomangam.client.domains.coupon;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.order.Order;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * coupon - order [N:M] 연관관계 설정을 위한 양방향 Mapper
 */
@Entity
@Table(name = "coupon_mapper_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class CouponMapper extends EntityAuditing {

    @ManyToOne
    @JoinColumn(name = "idx_coupon", nullable = false)
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name = "idx_order", nullable = false)
    private Order order;

    @Builder
    public CouponMapper(Coupon coupon, Order order) {
        this.coupon = coupon;
        this.order = order;
    }
}
