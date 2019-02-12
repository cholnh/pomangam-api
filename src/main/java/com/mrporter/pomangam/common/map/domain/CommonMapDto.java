package com.mrporter.pomangam.common.map.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonMapDto {
    private Integer idx;
    private String key;
    private String value;

    public CommonMap toEntity() {
        return null;
        /*
        return CommonMap.builder()
                .key(key)
                .value(value)
                .build();
                */
    }
}
