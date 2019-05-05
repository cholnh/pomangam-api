package com.mrporter.pomangam.storeEntry.store.domain;

import com.mrporter.pomangam.productEntry.product.domain.CategoryDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class StoreWithCategoryDto {
    private Integer idx;

    private String name;

    private Integer cnt_like;

    private Integer cnt_comment;

    private Double avg_star;

    private Byte type;

    private List<CategoryDto> categories;

    private Byte likeType;

    @Builder
    public StoreWithCategoryDto(Integer idx, String name, Integer cnt_like, Integer cnt_comment, Double avg_star, Byte type, List<CategoryDto> categories, Byte likeType) {
        this.idx = idx;
        this.name = name;
        this.cnt_like = cnt_like;
        this.cnt_comment = cnt_comment;
        this.avg_star = avg_star;
        this.type = type;
        this.categories = categories;
        this.likeType = likeType;
    }
}
