package com.mrporter.pomangam.client.domains.product;

import com.mrporter.pomangam.client.domains.product.category.ProductCategory;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long idxStore;
    private String name;
    private String description;
    private String subDescription;
    private ProductCategory productCategory;
    private ProductType productType;
    private Integer cntLike;
    private Integer sequence;

    public Product toEntity() {
        Product entity = new ModelMapper().map(this, Product.class);
        return entity;
    }
    
    public static ProductDto fromEntity(Product entity) {
        if(entity == null) return null;
        ProductDto dto = new ModelMapper().map(entity, ProductDto.class);
        return dto;
    }
    
    public static List<ProductDto> fromEntities(List<Product> entities) {
        if(entities == null) return null;
        List<ProductDto> dtos = new ArrayList<>();
        for(Product entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
