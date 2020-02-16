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
     */
    @Column(name="image_path", nullable = false)
    private String imagePath;

    /**
     * 대표이미지(ProductSubImageType.MAIN), 일반이미지(ProductSubImageType.SUB)
     */
    @Column(name="image_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreImageType imageType;

    /**
     * 순서
     */
    @Column(name = "sequence", nullable = false)
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
