package com.mrporter.pomangam.client.domains.product.sub;

import com.mrporter.pomangam.client.domains.product.sub.image.ProductSubImage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductSubDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private Long idxStore;
    private Integer salePrice;
    private String name;
    private String description;
    private String subDescription;
    private Integer sequence;
    private ProductSubType productSubType;
    private String productSubCategory;
    private Integer numberMinimum;
    private Integer numberMaximum;

    // images
    private String productImageMainPath;
    private List<String> productImageSubPaths = new ArrayList<>();

    public ProductSub toEntity() {
        ProductSub entity = new ModelMapper().map(this, ProductSub.class);
        return entity;
    }

    public static ProductSubDto fromEntity(ProductSub entity) {
        if(entity == null) return null;
        ProductSubDto dto = new ModelMapper().map(entity, ProductSubDto.class);

        List<ProductSubImage> productSubImages = entity.getImages();
        if(productSubImages != null && !productSubImages.isEmpty()) {
            for(ProductSubImage productSubImage : productSubImages) {
                switch (productSubImage.getImageType()) {
                    case MAIN:
                        dto.setProductImageMainPath(productSubImage.getImagePath());
                        break;
                    case SUB:
                        dto.getProductImageSubPaths().add(productSubImage.getImagePath());
                        break;
                }
            }
        }

        dto.setProductSubCategory(entity.getProductSubCategory().getCategoryTitle());
        dto.setSalePrice(entity.getCost().getSalePrice());

        return dto;
    }

    public static List<ProductSubDto> fromEntities(List<ProductSub> entities) {
        if(entities == null) return null;
        List<ProductSubDto> dtos = new ArrayList<>();
        for(ProductSub entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
