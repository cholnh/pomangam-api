package com.mrporter.pomangam.client.domains.notice;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class NoticeDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String title;
    private String contents;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    public Notice toEntity() {
        Notice entity = new ModelMapper().map(this, Notice.class);
        return entity;
    }

    public static NoticeDto fromEntity(Notice entity) {
        if(entity == null) return null;
        if(!entity.isValid()) return null;
        NoticeDto dto = new ModelMapper().map(entity, NoticeDto.class);
        return dto;
    }

    public static List<NoticeDto> fromEntities(List<Notice> entities) {
        if(entities == null) return null;
        List<NoticeDto> dtos = new ArrayList<>();
        for(Notice entity : entities) {
            NoticeDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
