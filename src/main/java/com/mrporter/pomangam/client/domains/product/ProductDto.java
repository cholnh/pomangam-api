package com.mrporter.pomangam.client.domains.product;

import com.mrporter.pomangam.client.domains.product.image.ProductImage;
import com.mrporter.pomangam.client.domains.product.info.ProductInfo;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategory;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategoryDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private Long idxStore;
    private Integer salePrice;
    private ProductInfo productInfo;
    private String productCategoryTitle;
    private Integer cntLike;
    private Integer cntReply;
    private Integer sequence;

    // images
    private String productImageMainPath;
    private List<String> productImageSubPaths = new ArrayList<>();

    // like
    private Boolean isLike;

    public Product toEntity() {
        Product entity = new ModelMapper().map(this, Product.class);
        return entity;
    }
    
    public static ProductDto fromEntity(Product entity) {
        if(entity == null) return null;
        ProductDto dto = new ModelMapper().map(entity, ProductDto.class);

        // images
        List<ProductImage> productImages = entity.getImages();
        if(productImages != null && !productImages.isEmpty()) {
            for(ProductImage productImage : productImages) {
                switch (productImage.getImageType()) {
                    case MAIN:
                        dto.setProductImageMainPath(productImage.getImagePath()+"?v="+productImage.getModifyDate());
                        break;
                    case SUB:
                        dto.getProductImageSubPaths().add(productImage.getImagePath()+"?v="+productImage.getModifyDate());
                        break;
                }
            }
        }

        // category
        dto.setProductCategoryTitle(entity.getProductCategory().getCategoryTitle());

        // cost
        dto.setSalePrice(entity.getCost().saleCost());

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
