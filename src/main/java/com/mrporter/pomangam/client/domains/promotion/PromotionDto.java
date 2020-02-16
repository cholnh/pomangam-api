package com.mrporter.pomangam.client.domains.promotion;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PromotionDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    public Promotion toEntity() {
        Promotion entity = new ModelMapper().map(this, Promotion.class);
        return entity;
    }

    public static PromotionDto fromEntity(Promotion entity) {
        if(entity == null) return null;
        PromotionDto dto = new ModelMapper().map(entity, PromotionDto.class);
        return dto;
    }

    public static List<PromotionDto> fromEntities(List<Promotion> entities) {
        if(entities == null) return null;
        List<PromotionDto> dtos = new ArrayList<>();
        for(Promotion entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
