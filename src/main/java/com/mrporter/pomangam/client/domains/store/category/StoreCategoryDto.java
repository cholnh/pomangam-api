package com.mrporter.pomangam.client.domains.store.category;

import com.mrporter.pomangam.client.domains.store.StoreDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class StoreCategoryDto implements Serializable {

    private Integer idx;

    private String categoryTitle;

    public StoreCategoryDto(Integer idx, String categoryTitle) {
        this.idx = idx;
        this.categoryTitle = categoryTitle;
    }

    public static StoreCategoryDto fromEntity(StoreCategory entity) {
        return new StoreCategoryDto(
            entity.getIdx(),
            entity.getCategoryTitle()
        );
    }

    public static List<StoreCategoryDto> fromEntities(List<StoreCategory>entities) {
        List<StoreCategoryDto> dtos = new ArrayList<>();
        for(StoreCategory entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
