package com.mrporter.pomangam.client.domains.deliverysite.region;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySiteDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class RegionDto implements Serializable {

    private Integer idx;

    private String title;

    public RegionDto(Integer idx, String title) {
        this.idx = idx;
        this.title = title;
    }

    public Region toEntity() {
        return Region.builder()
                .title(title)
                .build();
    }

    public static RegionDto fromEntity(Region entity) {
        return new RegionDto(
            entity.getIdx(),
            entity.getTitle()
        );
    }

    public static List<RegionDto> fromEntities(List<Region>entities) {
        List<RegionDto> dtos = new ArrayList<>();
        for(Region entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}