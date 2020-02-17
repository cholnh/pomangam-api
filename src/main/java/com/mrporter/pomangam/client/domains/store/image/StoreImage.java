package com.mrporter.pomangam.client.domains.store.image;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "store_image_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class StoreImage extends EntityAuditing {

    /**
     * 이미지 경로
     * 글자수: utf8 기준 / 영문 100자 / 한글 100자
     */
    @Column(name="image_path", nullable = false, length = 100)
    private String imagePath;

    /**
     * 대표이미지(StoreImageType.MAIN), 일반이미지(StoreImageType.SUB)
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name="image_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StoreImageType imageType;

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
    public StoreImage(String imagePath, StoreImageType imageType, Integer sequence) {
        this.imagePath = imagePath;
        this.imageType = imageType;
        this.sequence = sequence;
    }
}
