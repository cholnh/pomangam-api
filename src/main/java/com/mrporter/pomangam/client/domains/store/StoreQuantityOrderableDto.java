package com.mrporter.pomangam.client.domains.store;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class StoreQuantityOrderableDto implements Serializable {

    private Long idx;
    private Integer quantityOrderable;  // 주문 가능 수량

    @Builder
    public StoreQuantityOrderableDto(Long idx, Integer quantityOrderable) {
        this.idx = idx;
        this.quantityOrderable = quantityOrderable;
    }
}
