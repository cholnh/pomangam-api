package com.mrporter.pomangam.client.domains.store;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String name;
    private String description;
    private String subDescription;
    private Float avgStar;
    private Integer cntLike;
    private Integer cntComment;

    public Store toEntity() {
        Store entity = new ModelMapper().map(this, Store.class);
        return entity;
    }

    public static StoreDto fromEntity(Store entity) {
        if(entity == null) return null;
        StoreDto dto = new ModelMapper().map(entity, StoreDto.class);
        return dto;
    }

    public static List<StoreDto> fromEntities(List<Store> entities) {
        if(entities == null) return null;
        List<StoreDto> dtos = new ArrayList<>();
        for(Store entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
