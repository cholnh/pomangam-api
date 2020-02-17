package com.mrporter.pomangam.client.domains.deliverysite.region;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RegionDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String name;

    public Region toEntity() {
        Region entity = new ModelMapper().map(this, Region.class);
        return entity;
    }

    public static RegionDto fromEntity(Region entity) {
        if(entity == null) return null;
        RegionDto dto = new ModelMapper().map(entity, RegionDto.class);
        return dto;
    }

    public static List<RegionDto> fromEntities(List<Region> entities) {
        if(entities == null) return null;
        List<RegionDto> dtos = new ArrayList<>();
        for(Region entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}