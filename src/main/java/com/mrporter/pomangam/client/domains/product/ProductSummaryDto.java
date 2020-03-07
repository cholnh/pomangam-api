package com.mrporter.pomangam.client.domains.product;

import com.mrporter.pomangam.client.domains.product.image.ProductImage;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductSummaryDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private int salePrice;
    private String name;
    private String productImageMainPath;

    public static ProductSummaryDto fromEntity(Product entity) {
        if(entity == null) return null;
        ProductSummaryDto dto = new ModelMapper().map(entity, ProductSummaryDto.class);

        // images
        List<ProductImage> productImages = entity.getImages();
        if(productImages != null && !productImages.isEmpty()) {
            for(ProductImage productImage : productImages) {
                switch (productImage.getImageType()) {
                    case MAIN:
                        dto.setProductImageMainPath(productImage.getImagePath());
                        break;
                    default:
                        break;
                }
            }
        }

        // name
        dto.setName(entity.getProductInfo().getName());

        // cost
        dto.setSalePrice(entity.getCost().saleCost());

        return dto;
    }

    public static List<ProductSummaryDto> fromEntities(List<Product> entities) {
        if(entities == null) return null;
        List<ProductSummaryDto> dtos = new ArrayList<>();
        for(Product entity : entities) {
            ProductSummaryDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
