package com.mrporter.pomangam.client.domains.carte.image;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CarteImageDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String imagePath;
    private CarteImageType imageType;

    public CarteImage toEntity() {
        CarteImage entity = new ModelMapper().map(this, CarteImage.class);
        return entity;
    }

    public static CarteImageDto fromEntity(CarteImage entity) {
        if(entity == null) return null;
        CarteImageDto dto = new ModelMapper().map(entity, CarteImageDto.class);
        return dto;
    }

    public static List<CarteImageDto> fromEntities(List<CarteImage> entities) {
        if(entities == null) return null;
        List<CarteImageDto> dtos = new ArrayList<>();
        for(CarteImage entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
