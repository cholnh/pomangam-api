package com.mrporter.pomangam.client.domains.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DeliverySiteDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String name;
    private DeliveryType deliveryType;
    private String location;
    private String campus;
    private Long idxRegion;

    public DeliverySite toEntity() {
        DeliverySite entity = new ModelMapper().map(this, DeliverySite.class);
        Region region = Region.builder().build();
        region.setIdx(idxRegion);
        entity.setRegion(region);
        return entity;
    }

    public static DeliverySiteDto fromEntity(DeliverySite entity) {
        if(entity.getIdx() == 0) return null;
        DeliverySiteDto dto = new ModelMapper().map(entity, DeliverySiteDto.class);
        dto.setIdxRegion(entity.getRegion().getIdx());
        return dto;
    }

    public static List<DeliverySiteDto> fromEntities(List<DeliverySite> entities) {
        List<DeliverySiteDto> dtos = new ArrayList<>();
        for(DeliverySite entity : entities) {
            if(entity.getIdx() != 0) {
                dtos.add(fromEntity(entity));
            }
        }
        return dtos;
    }
}