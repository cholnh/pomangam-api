package com.mrporter.pomangam.client.domains.store.review.image;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreReviewImageDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String imagePath;
    private StoreReviewImageType imageType;

    public StoreReviewImage toEntity() {
        StoreReviewImage entity = new ModelMapper().map(this, StoreReviewImage.class);
        return entity;
    }

    public static StoreReviewImageDto fromEntity(StoreReviewImage entity) {
        if(entity == null) return null;
        StoreReviewImageDto dto = new ModelMapper().map(entity, StoreReviewImageDto.class);
        return dto;
    }

    public static List<StoreReviewImageDto> fromEntities(List<StoreReviewImage> entities) {
        if(entities == null) return null;
        List<StoreReviewImageDto> dtos = new ArrayList<>();
        for(StoreReviewImage entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}