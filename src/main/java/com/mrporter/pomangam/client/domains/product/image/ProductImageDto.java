package com.mrporter.pomangam.client.domains.product.image;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductImageDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String imagePath;
    private ProductImageType imageType;

    public ProductImage toEntity() {
        ProductImage entity = new ModelMapper().map(this, ProductImage.class);
        return entity;
    }

    public static ProductImageDto fromEntity(ProductImage entity) {
        if(entity == null) return null;
        ProductImageDto dto = new ModelMapper().map(entity, ProductImageDto.class);
        return dto;
    }

    public static List<ProductImageDto> fromEntities(List<ProductImage> entities) {
        if(entities == null) return null;
        List<ProductImageDto> dtos = new ArrayList<>();
        for(ProductImage entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
