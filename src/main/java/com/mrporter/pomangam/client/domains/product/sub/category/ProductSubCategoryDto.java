package com.mrporter.pomangam.client.domains.product.sub.category;

import com.mrporter.pomangam.client.domains.product.sub.ProductSubDto;
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
    private List<ProductSubDto> productSubs;

    public ProductSubCategory toEntity() {
        ProductSubCategory entity = new ModelMapper().map(this, ProductSubCategory.class);
        return entity;
    }

    public static ProductSubCategoryDto fromEntity(ProductSubCategory entity) {
        if(entity == null) return null;
        ProductSubCategoryDto dto = new ProductSubCategoryDto();
        dto.setIdx(entity.getIdx());
        dto.setRegisterDate(entity.getRegisterDate());
        dto.setModifyDate(entity.getModifyDate());
        dto.setCategoryTitle(entity.getCategoryTitle());
        dto.setProductSubs(ProductSubDto.fromEntities(entity.getProductSubs()));
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