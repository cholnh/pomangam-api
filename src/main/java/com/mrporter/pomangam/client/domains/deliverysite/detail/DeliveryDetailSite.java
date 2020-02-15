package com.mrporter.pomangam.client.domains.deliverysite.detail;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "delivery_detail_site_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"deliverySite"})
public class DeliveryDetailSite extends EntityAuditing {

    /**
     * 배달지
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_delivery_site")
    private DeliverySite deliverySite;

    /**
     * 배달지명
     * 배달이 도착하는 기관의 이름
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 상세 주소 설명
     */
    @Column(name = "location", nullable = false)
    private String location;

    /**
     * 순서
     */
    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    /**
     * 배달 추가 시간
     */
    @Column(name = "additional_time", nullable = false)
    private LocalTime additionalTime;

    /**
     * gps 위도
     */
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    /**
     * gps 경도
     */
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    /**
     * 축약어
     */
    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    @Builder
    public DeliveryDetailSite(DeliverySite deliverySite, String name, String location, Integer sequence, LocalTime additionalTime, Double latitude, Double longitude, String abbreviation) {
        this.deliverySite = deliverySite;
        this.name = name;
        this.location = location;
        this.sequence = sequence;
        this.additionalTime = additionalTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.abbreviation = abbreviation;
    }
}
