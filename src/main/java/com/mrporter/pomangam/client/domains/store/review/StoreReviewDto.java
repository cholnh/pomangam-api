package com.mrporter.pomangam.client.domains.store.review;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreReviewDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    public StoreReview toEntity() {
        StoreReview entity = new ModelMapper().map(this, StoreReview.class);
        return entity;
    }

    public static StoreReviewDto fromEntity(StoreReview entity) {
        if(entity == null) return null;
        StoreReviewDto dto = new ModelMapper().map(entity, StoreReviewDto.class);
        return dto;
    }

    public static List<StoreReviewDto> fromEntities(List<StoreReview> entities) {
        if(entities == null) return null;
        List<StoreReviewDto> dtos = new ArrayList<>();
        for(StoreReview entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}