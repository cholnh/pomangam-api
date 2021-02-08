package com.mrporter.pomangam.client.domains.carte.item;

import com.mrporter.pomangam.client.domains.carte.CarteType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CarteItemDto implements Serializable {
    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String name;
    private CarteType carteType;

    public CarteItem toEntity() {
        CarteItem entity = new ModelMapper().map(this, CarteItem.class);
        return entity;
    }

    public static CarteItemDto fromEntity(CarteItem entity) {
        CarteItemDto dto = new ModelMapper().map(entity, CarteItemDto.class);
        return dto;
    }

    public static List<CarteItemDto> fromEntities(List<CarteItem> entities) {
        List<CarteItemDto> dtos = new ArrayList<>();
        for(CarteItem entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }

}
