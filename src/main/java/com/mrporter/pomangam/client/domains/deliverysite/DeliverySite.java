package com.mrporter.pomangam.client.domains.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.user.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Table(name = "delivery_site_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@ToString(exclude = {"region", "stores", "detailSites", "users"})
@DynamicUpdate
public class DeliverySite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    /**
     * 배달지명
     * 배달이 도착하는 기관의 이름
     */
    @NotBlank
    private String title;

    /**
     * 배달지 주소
     */
    private String location;

    /**
     * 캠퍼스 이름
     * 대학교 캠퍼스 등 기관의 하위 카테고리
     */
    private String campus;

    /**
     * 지역 카테고리 명
     * optional : 해당 객체 nullable true/false
     */
    @JoinColumn(name = "idx_region")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Region region;

    @OneToMany(mappedBy = "deliverySite", fetch = FetchType.LAZY)
    private List<Store> stores;

    @OneToMany(mappedBy = "deliverySite", fetch = FetchType.LAZY)
    private List<DeliveryDetailSite> detailSites;

    @Builder
    public DeliverySite(@NotBlank String title, String location, String campus, Region region, List<Store> stores, List<DeliveryDetailSite> detailSites) {
        this.title = title;
        this.location = location;
        this.campus = campus;
        this.region = region;
        if(stores != null) {
            this.stores = stores;
        }
        if(detailSites != null) {
            this.detailSites = detailSites;
        }
    }
}
