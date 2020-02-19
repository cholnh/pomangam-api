package com.mrporter.pomangam.client.domains.ordertime;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.store.Store;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * orderTime - store [N:M] 연관관계 설정을 위한 양방향 Mapper
 */
@Entity
@Table(name = "order_time_mapper_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class OrderTimeMapper extends EntityAuditing {

    @ManyToOne
    @JoinColumn(name = "idx_order_time")
    private OrderTime orderTime;

    @ManyToOne
    @JoinColumn(name = "idx_store")
    private Store store;

    @Builder
    public OrderTimeMapper(OrderTime orderTime, Store store) {
        this.orderTime = orderTime;
        this.store = store;
    }
}
