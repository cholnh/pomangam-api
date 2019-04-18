package com.mrporter.pomangam.productEntry.product.domain;

import com.mrporter.pomangam.storeEntry.store.domain.StoreDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data

public class SearchProductDto implements Serializable {

    private List<StoreDto> storeDtoList;

    private List<ProductSummaryDto> productSummaryDtoList;

    public SearchProductDto(List<StoreDto> storeDtoList, List<ProductSummaryDto> productSummaryDtoList) {
        this.storeDtoList = storeDtoList;
        this.productSummaryDtoList = productSummaryDtoList;
    }
}
