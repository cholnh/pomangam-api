package com.mrporter.pomangam.client.domains.advertisement;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "advertisement_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Advertisement extends EntityAuditing {

    /**
     * 광고 타입
     */
    @Column(name = "advertisement_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private AdvertisementType advertisementType;

    /**
     * 이미지 경로
     * 글자수: utf8 기준 / 영문 100자 / 한글 100자
     */
    @Column(name="image_path", nullable = false, length = 100)
    private String imagePath;

    /**
     * 클릭 후 이벤트
     * 글자수: utf8 기준 / 영문 100자 / 한글 100자
     */
    @Column(name = "next_location", nullable = true, length = 100)
    private String nextLocation;

    /**
     * 순서
     */
    @Column(name = "sequence", nullable = false, columnDefinition = "INT default 0")
    private Integer sequence;

    @PrePersist
    private void prePersist() {
        this.sequence = sequence == null ? 0 : sequence;
    }

    @Builder
    public Advertisement(Long idx, AdvertisementType advertisementType, String imagePath, String nextLocation, Integer sequence) {
        super.setIdx(idx);
        this.advertisementType = advertisementType;
        this.imagePath = imagePath;
        this.nextLocation = nextLocation;
        this.sequence = sequence;
    }
}
