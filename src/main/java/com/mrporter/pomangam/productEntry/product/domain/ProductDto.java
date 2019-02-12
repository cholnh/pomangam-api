package com.mrporter.pomangam.productEntry.product.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ProductDto implements Serializable {

    private Integer idx;

    private Integer store_idx;

    private String name;

    private String description;

    private String sub_description;

    private Integer category_id;

    private String category_name;

    private Byte state_active;

    private Byte type;

    private Integer sequence;

    private Integer like_count;

    public Product toEntity() {
        return null;
    }
}