package com.mrporter.pomangam.client.domains.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import com.mrporter.pomangam.client.domains.deliverysite.region.RegionDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class DeliverySiteDto implements Serializable {

    private Integer idx;

    private String title;

    private String location;

    private String campus;

    //private RegionDto region;
    private Integer idxRegion;

    public DeliverySite toEntity() {
        DeliverySite entity = new ModelMapper().map(this, DeliverySite.class);
        Region region = Region.builder().build();
        region.setIdx(entity.getRegion().getIdx());
        entity.setRegion(region);
        return entity;
    }

    public static DeliverySiteDto fromEntity(DeliverySite entity) {
        DeliverySiteDto dto = new ModelMapper().map(entity, DeliverySiteDto.class);
        dto.setIdxRegion(entity.getRegion().getIdx());
        return dto;
    }

    public static List<DeliverySiteDto> fromEntities(List<DeliverySite> entities) {
        List<DeliverySiteDto> dtos = new ArrayList<>();
        for(DeliverySite entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}