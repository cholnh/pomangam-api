package com.mrporter.pomangam.client.domains.deliverysite.detail;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "delivery_detail_site_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@ToString(exclude = {"deliverySite"})
@DynamicUpdate
public class DeliveryDetailSite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

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
    @NotBlank
    private String title;

    /**
     * 상세 주소 설명
     */
    @NotBlank
    private String location;

    /**
     * 순서
     */
    @ColumnDefault("0")
    private Integer sequence;

    /**
     * 배달 추가 시간
     */
    private LocalTime additionalTime;

    /**
     * gps 위도
     */
    private Double latitude;

    /**
     * gps 경도
     */
    private Double longitude;

    /**
     * 축약어
     */
    @NotBlank
    private String abbreviation;

    @OneToMany(mappedBy = "deliveryDetailSite", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    @Builder
    public DeliveryDetailSite(DeliverySite deliverySite, @NotBlank String title, @NotBlank String location, Integer sequence, LocalTime additionalTime, Double latitude, Double longitude, @NotBlank String abbreviation, List<User> users) {
        this.deliverySite = deliverySite;
        this.title = title;
        this.location = location;
        this.sequence = sequence;
        this.additionalTime = additionalTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.abbreviation = abbreviation;
        if(users != null) {
            this.users = users;
        }
    }
}
