package com.mrporter.pomangam.feedbackHistory.commentAll.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class StoreCategoryDto implements Serializable {

    private Integer store_idx;

    private String store_name;

    @Builder
    public StoreCategoryDto(Integer store_idx, String store_name) {
        this.store_idx = store_idx;
        this.store_name = store_name;
    }
}