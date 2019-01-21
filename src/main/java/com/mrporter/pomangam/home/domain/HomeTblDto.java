package com.mrporter.pomangam.home.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeTblDto {

    Long idx;
    String name;
    String desc;

    public HomeTbl toEntity() {
        return HomeTbl.builder()
                .name(name)
                .desc(desc)
                .build();
    }
}
