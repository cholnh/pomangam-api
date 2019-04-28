package com.mrporter.pomangam.productEntry.product.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProductViewDto {
    List<CategoryDto> categories;
    List<ProductWithCostDto> products;

    public ProductViewDto(List<CategoryDto> categories, List<ProductWithCostDto> products) {
        this.categories = categories;
        this.products = products;
    }
}
