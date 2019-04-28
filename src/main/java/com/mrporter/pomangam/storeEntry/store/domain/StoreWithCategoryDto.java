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

    private Byte type;

    private List<CategoryDto> categories;

    @Builder
    public StoreWithCategoryDto(Integer idx, String name, Integer cnt_like, Integer cnt_comment, Byte type, List<CategoryDto> categories) {
        this.idx = idx;
        this.name = name;
        this.cnt_like = cnt_like;
        this.cnt_comment = cnt_comment;
        this.type = type;
        this.categories = categories;
    }
}
