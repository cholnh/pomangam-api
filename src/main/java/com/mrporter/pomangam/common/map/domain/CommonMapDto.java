package com.mrporter.pomangam.common.map.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonMapDto {
    private Long idx;
    private String key;
    private String value;

    public CommonMap toEntity() {
        return CommonMap.builder()
                .key(key)
                .value(value)
                .build();
    }
}
