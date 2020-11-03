package com.mrporter.pomangam.client.domains.store;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * deliverySite - store [N:M] 연관관계 설정을 위한 양방향 Mapper
 */
@Entity
@Table(name = "store_mapper_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class StoreMapper extends EntityAuditing {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_delivery_site", nullable = false)
    private DeliverySite deliverySite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_store", nullable = false)
    private Store store;

    @Builder
    public StoreMapper(DeliverySite deliverySite, Store store) {
        this.deliverySite = deliverySite;
        this.store = store;
    }
}
