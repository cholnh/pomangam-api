package com.mrporter.pomangam.client.domains.deliverysite.region;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "region_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "deliverySites")
public class Region extends EntityAuditing {

    /**
     * 지역명
     */
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<DeliverySite> deliverySites;

    @Builder
    public Region(String name, List<DeliverySite> deliverySites) {
        this.name = name;
        if(deliverySites != null) {
            this.deliverySites = deliverySites;
        }
    }
}
