package com.mrporter.pomangam.product.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ProductDto implements Serializable {

    private Long idx;

    private Long store_idx;

    private String name;

    private String description;

    private String sub_description;

    private Long category_id;

    private String category_name;

    private Integer state_active;

    private Integer type;

    private Long sequence;

    private Long like_count;

    public Product toEntity() {
        return null;
    }
}