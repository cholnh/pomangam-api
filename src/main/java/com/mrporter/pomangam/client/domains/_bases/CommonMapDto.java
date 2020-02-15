package com.mrporter.pomangam.client.domains._bases;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CommonMapDto implements Serializable {
    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String key;
    private String value;

    public CommonMap toEntity() {
        CommonMap entity = new ModelMapper().map(this, CommonMap.class);
        return entity;
    }

    public static CommonMapDto fromEntity(CommonMap entity) {
        if(entity == null) return null;
        CommonMapDto dto = new ModelMapper().map(entity, CommonMapDto.class);
        return dto;
    }

    public static List<CommonMapDto> fromEntities(List<CommonMap> entities) {
        if(entities == null) return null;
        List<CommonMapDto> dtos = new ArrayList<>();
        for(CommonMap entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
