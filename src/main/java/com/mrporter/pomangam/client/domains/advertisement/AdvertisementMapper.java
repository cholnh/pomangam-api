package com.mrporter.pomangam.client.domains.advertisement;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * advertisement - deliverySite [N:M] 연관관계 설정을 위한 양방향 Mapper
 */
@Entity
@Table(name = "advertisement_mapper_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AdvertisementMapper extends EntityAuditing {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_advertisement", nullable = false)
    private Advertisement advertisement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_delivery_site", nullable = false)
    private DeliverySite deliverySite;

    @Builder
    public AdvertisementMapper(Advertisement advertisement, DeliverySite deliverySite) {
        this.advertisement = advertisement;
        this.deliverySite = deliverySite;
    }
}
