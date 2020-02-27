package com.mrporter.pomangam.client.domains.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EventViewDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String title;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    public static EventViewDto fromEntity(Event entity) {
        if(entity == null) return null;
        if(!entity.isValid()) return null;
        EventViewDto dto = new ModelMapper().map(entity, EventViewDto.class);
        return dto;
    }

    public static List<EventViewDto> fromEntities(List<Event> entities) {
        if(entities == null) return null;
        List<EventViewDto> dtos = new ArrayList<>();
        for(Event entity : entities) {
            EventViewDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
