package com.mrporter.pomangam.client.domains.store.story;

import com.mrporter.pomangam.client.domains.store.story.image.StoreStoryImage;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreStoryDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String title;
    private List<String> images = new ArrayList<>();
    private Integer sequence;

    public StoreStory toEntity() {
        StoreStory entity = new ModelMapper().map(this, StoreStory.class);
        return entity;
    }

    public static StoreStoryDto fromEntity(StoreStory entity) {
        if(entity == null) return null;
        StoreStoryDto dto = new ModelMapper().map(entity, StoreStoryDto.class);

        // images
        dto.getImages().clear();
        List<StoreStoryImage> storyImages = entity.getImages();
        if(storyImages != null && !storyImages.isEmpty()) {
            for (StoreStoryImage storyImage : storyImages) {
                dto.getImages().add(storyImage.getImagePath());
            }
        }
        return dto;
    }

    public static List<StoreStoryDto> fromEntities(List<StoreStory> entities) {
        if(entities == null) return null;
        List<StoreStoryDto> dtos = new ArrayList<>();
        for(StoreStory entity : entities) {
            StoreStoryDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
