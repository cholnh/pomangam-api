package com.mrporter.pomangam.client.domains.ordertime;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.store.Store;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * orderTime - deliverySite [N:M] 연관관계 설정을 위한 양방향 Mapper
 */
@Entity
@Table(name = "order_time_delivery_site_mapper_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class OrderTimeDeliverySiteMapper extends EntityAuditing {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_order_time", nullable = false)
    private OrderTime orderTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_delivery_site", nullable = false)
    private DeliverySite deliverySite;

    @Builder
    public OrderTimeDeliverySiteMapper(OrderTime orderTime, DeliverySite deliverySite) {
        this.orderTime = orderTime;
        this.deliverySite = deliverySite;
    }
}
