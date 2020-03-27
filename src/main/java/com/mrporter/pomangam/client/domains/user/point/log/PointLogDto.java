package com.mrporter.pomangam.client.domains.user.point.log;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PointLogDto implements Serializable {

    private Long idx;
    private Long idxOrder;
    private Long idxUser;
    private PointType pointType;
    private Integer point;
    private Integer postPoint;
    private LocalDateTime registerDate;
    private LocalDateTime expiredDate;

    public PointLog toEntity() {
        PointLog entity = new ModelMapper().map(this, PointLog.class);
        return entity;
    }

    public static PointLogDto fromEntity(PointLog entity) {
        if(entity == null) return null;
        if(!entity.isValid()) return null;
        PointLogDto dto = new ModelMapper().map(entity, PointLogDto.class);
        return dto;
    }

    public static List<PointLogDto> fromEntities(List<PointLog> entities) {
        if(entities == null) return null;
        List<PointLogDto> dtos = new ArrayList<>();
        for(PointLog entity : entities) {
            PointLogDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
