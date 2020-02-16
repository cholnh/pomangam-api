package com.mrporter.pomangam.client.domains.product.sub.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductSubCategoryDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String categoryTitle;

    public ProductSubCategory toEntity() {
        ProductSubCategory entity = new ModelMapper().map(this, ProductSubCategory.class);
        return entity;
    }

    public static ProductSubCategoryDto fromEntity(ProductSubCategory entity) {
        if(entity == null) return null;
        ProductSubCategoryDto dto = new ModelMapper().map(entity, ProductSubCategoryDto.class);
        return dto;
    }

    public static List<ProductSubCategoryDto> fromEntities(List<ProductSubCategory> entities) {
        if(entities == null) return null;
        List<ProductSubCategoryDto> dtos = new ArrayList<>();
        for(ProductSubCategory entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}