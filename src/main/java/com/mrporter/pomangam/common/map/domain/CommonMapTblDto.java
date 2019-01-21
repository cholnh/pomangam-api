package com.mrporter.pomangam.common.map.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonMapTblDto {
    private Long idx;
    private String key;
    private String value;

    public CommonMapTbl toEntity() {
        return CommonMapTbl.builder()
                .key(key)
                .value(value)
                .build();
    }
}
