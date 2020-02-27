package com.mrporter.pomangam.client.domains.notice;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class NoticeViewDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String title;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    public static NoticeViewDto fromEntity(Notice entity) {
        if(entity == null) return null;
        if(!entity.isValid()) return null;
        NoticeViewDto dto = new ModelMapper().map(entity, NoticeViewDto.class);
        return dto;
    }

    public static List<NoticeViewDto> fromEntities(List<Notice> entities) {
        if(entities == null) return null;
        List<NoticeViewDto> dtos = new ArrayList<>();
        for(Notice entity : entities) {
            NoticeViewDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
