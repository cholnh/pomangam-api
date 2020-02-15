package com.mrporter.pomangam.client.domains.store.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreCategoryDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String categoryTitle;

    public StoreCategory toEntity() {
        StoreCategory entity = new ModelMapper().map(this, StoreCategory.class);
        return entity;
    }

    public static StoreCategoryDto fromEntity(StoreCategory entity) {
        if(entity == null) return null;
        StoreCategoryDto dto = new ModelMapper().map(entity, StoreCategoryDto.class);
        return dto;
    }

    public static List<StoreCategoryDto> fromEntities(List<StoreCategory> entities) {
        if(entities == null) return null;
        List<StoreCategoryDto> dtos = new ArrayList<>();
        for(StoreCategory entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
