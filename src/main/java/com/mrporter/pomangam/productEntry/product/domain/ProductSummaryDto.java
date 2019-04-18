package com.mrporter.pomangam.productEntry.product.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ProductSummaryDto implements Serializable {

    private Integer product_idx;

    private Integer store_idx;

    private String store_name;

    private String product_name;

    private Integer category_id;

    private String category_name;

    private Byte product_type;

    public ProductSummaryDto(Integer product_idx, Integer store_idx, String store_name, String product_name, Integer category_id, String category_name, Byte product_type) {
        this.product_idx = product_idx;
        this.store_idx = store_idx;
        this.store_name = store_name;
        this.product_name = product_name;
        this.category_id = category_id;
        this.category_name = category_name;
        this.product_type = product_type;
    }
}