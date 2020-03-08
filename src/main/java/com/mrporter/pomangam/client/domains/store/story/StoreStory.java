package com.mrporter.pomangam.client.domains.store.story;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.store.story.image.StoreStoryImage;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "store_story_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class StoreStory extends EntityAuditing {

    /**
     * 제목
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "title", nullable = false, length = 20)
    private String title;

    /**
     * 이미지 정보
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_store_story", nullable = false)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    private List<StoreStoryImage> images = new ArrayList<>();

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
    public StoreStory(String title, List<StoreStoryImage> images, Integer sequence) {
        this.title = title;
        this.images = images;
        this.sequence = sequence;
    }

    public void addImages(StoreStoryImage...storyImage) {
        if(this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.addAll(Arrays.asList(storyImage));
    }
}
