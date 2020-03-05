package com.mrporter.pomangam.client.domains.store.story.image;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "store_story_image_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class StoreStoryImage extends EntityAuditing {

    /**
     * 이미지 경로
     * 글자수: utf8 기준 / 영문 100자 / 한글 100자
     */
    @Column(name="image_path", nullable = false, length = 100)
    private String imagePath;

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
    public StoreStoryImage(String imagePath, Integer sequence) {
        this.imagePath = imagePath;
        this.sequence = sequence;
    }
}
