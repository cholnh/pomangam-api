package com.mrporter.pomangam.client.domains.deliverysite;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import com.mrporter.pomangam.client.domains.store.Store;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery_site_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"region", "stores", "detailSites"})
public class DeliverySite extends EntityAuditing {

    /**
     * 배달지명
     * 배달이 도착하는 기관의 이름
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 배달지 주소
     */
    @Column(name = "location", nullable = false)
    private String location;

    /**
     * 캠퍼스 이름
     * 대학교 캠퍼스 등 기관의 하위 카테고리
     */
    @Column(name = "campus", nullable = true)
    private String campus;

    /**
     * 지역 카테고리 명
     * optional : 해당 객체 nullable true/false
     */
    @JoinColumn(name = "idx_region")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Region region;

    @OneToMany(mappedBy = "deliverySite", fetch = FetchType.LAZY)
    private List<DeliveryDetailSite> detailSites;

    @PrePersist
    private void prePersist() {
        this.campus = this.campus == null ? "본캠" : this.campus;
    }

    @Builder
    public DeliverySite(String name, String location, String campus, Region region, List<DeliveryDetailSite> detailSites) {
        this.name = name;
        this.location = location;
        this.campus = campus;
        this.region = region;
        if(detailSites != null) {
            this.detailSites = detailSites;
        }
    }
}
