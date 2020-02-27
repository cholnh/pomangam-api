package com.mrporter.pomangam.client.domains.event;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EventDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String title;
    private String contents;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    public Event toEntity() {
        Event entity = new ModelMapper().map(this, Event.class);
        return entity;
    }

    public static EventDto fromEntity(Event entity) {
        if(entity == null) return null;
        if(!entity.isValid()) return null;
        EventDto dto = new ModelMapper().map(entity, EventDto.class);
        return dto;
    }

    public static List<EventDto> fromEntities(List<Event> entities) {
        if(entities == null) return null;
        List<EventDto> dtos = new ArrayList<>();
        for(Event entity : entities) {
            EventDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
