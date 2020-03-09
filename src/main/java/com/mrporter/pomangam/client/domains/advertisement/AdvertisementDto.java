package com.mrporter.pomangam.client.domains.advertisement;

import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AdvertisementDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private AdvertisementType advertisementType;
    private String imagePath;
    private String nextLocation;
    private Integer sequence;

    public Advertisement toEntity() {
        Advertisement entity = new ModelMapper().map(this, Advertisement.class);
        return entity;
    }

    public static AdvertisementDto fromEntity(Advertisement entity) {
        if(entity == null) return null;
        AdvertisementDto dto = new ModelMapper().map(entity, AdvertisementDto.class);

        dto.setImagePath(entity.getImagePath()+"?v="+entity.getModifyDate());

        return dto;
    }

    public static List<AdvertisementDto> fromEntities(List<Advertisement> entities) {
        if(entities == null) return null;
        List<AdvertisementDto> dtos = new ArrayList<>();
        for(Advertisement entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
