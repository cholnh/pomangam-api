package com.mrporter.pomangam.client.domains.store;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreDto implements Serializable {

    private Integer idx;

    private String title;

    private String description;

    private String subDescription;

    private Float avgStar;

    private Integer cntLike;

    private Integer cntComment;

    public static StoreDto fromEntity(Store entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, StoreDto.class);
    }

    public static List<StoreDto> fromEntities(List<Store> entities) {
        List<StoreDto> dtos = new ArrayList<>();
        for(Store entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
