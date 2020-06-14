package com.mrporter.pomangam.client.domains.deliverysite;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
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
@ToString(exclude = {"region", "detailSites"})
public class DeliverySite extends EntityAuditing {

    /**
     * 배달지명
     * 배달이 도착하는 기관의 이름
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    /**
     * 배달 타입
     */
    @Column(name = "delivery_type", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    /**
     * 배달지 주소
     * 글자수: utf8 기준 / 영문 100자 / 한글 10자
     */
    @Column(name = "location", nullable = false, length = 100)
    private String location;

    /**
     * 캠퍼스 이름
     * 대학교 캠퍼스 등 기관의 하위 카테고리
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "campus", nullable = true, length = 20)
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
    public DeliverySite(Long idx, String name, DeliveryType deliveryType, String location, String campus, Region region, List<DeliveryDetailSite> detailSites) {
        super.setIdx(idx);
        this.name = name;
        this.deliveryType = deliveryType;
        this.location = location;
        this.campus = campus;
        this.region = region;
        if(detailSites != null) {
            this.detailSites = detailSites;
        }
    }
}
