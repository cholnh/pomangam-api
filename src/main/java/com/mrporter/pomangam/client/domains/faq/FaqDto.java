package com.mrporter.pomangam.client.domains.faq;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FaqDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String title;
    private String contents;

    public Faq toEntity() {
        Faq entity = new ModelMapper().map(this, Faq.class);
        return entity;
    }

    public static FaqDto fromEntity(Faq entity) {
        if(entity == null) return null;
        FaqDto dto = new ModelMapper().map(entity, FaqDto.class);
        return dto;
    }

    public static List<FaqDto> fromEntities(List<Faq> entities) {
        if(entities == null) return null;
        List<FaqDto> dtos = new ArrayList<>();
        for(Faq entity : entities) {
            FaqDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
