package com.mrporter.pomangam.common.map.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonMapDto {
    private Integer idx;
    private String key;
    private String value;

    public CommonMapDto(Integer idx, String key, String value) {
        this.idx = idx;
        this.key = key;
        this.value = value;
    }
}
