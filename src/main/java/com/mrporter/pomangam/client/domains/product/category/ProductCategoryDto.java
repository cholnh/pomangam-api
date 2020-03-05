package com.mrporter.pomangam.client.domains.product.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductCategoryDto implements Serializable {

    private Long idx;
    private String categoryTitle;

    public ProductCategory toEntity() {
        ProductCategory entity = new ModelMapper().map(this, ProductCategory.class);
        return entity;
    }

    public static ProductCategoryDto fromEntity(ProductCategory entity) {
        if(entity == null) return null;
        ProductCategoryDto dto = new ModelMapper().map(entity, ProductCategoryDto.class);
        return dto;
    }

    public static List<ProductCategoryDto> fromEntities(List<ProductCategory> entities) {
        if(entities == null) return null;
        List<ProductCategoryDto> dtos = new ArrayList<>();
        for(ProductCategory entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}