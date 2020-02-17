package com.mrporter.pomangam.client.domains.product.sub.image;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductSubImageDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String imagePath;
    private ProductSubImageType imageType;

    public ProductSubImage toEntity() {
        ProductSubImage entity = new ModelMapper().map(this, ProductSubImage.class);
        return entity;
    }

    public static ProductSubImageDto fromEntity(ProductSubImage entity) {
        if(entity == null) return null;
        ProductSubImageDto dto = new ModelMapper().map(entity, ProductSubImageDto.class);
        return dto;
    }

    public static List<ProductSubImageDto> fromEntities(List<ProductSubImage> entities) {
        if(entities == null) return null;
        List<ProductSubImageDto> dtos = new ArrayList<>();
        for(ProductSubImage entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
