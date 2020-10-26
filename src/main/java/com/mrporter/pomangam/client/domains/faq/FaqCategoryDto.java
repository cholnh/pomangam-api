package com.mrporter.pomangam.client.domains.faq;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FaqCategoryDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String title;
    private List<FaqDto> faqs = new ArrayList<>();

    public FaqCategory toEntity() {
        FaqCategory entity = new ModelMapper().map(this, FaqCategory.class);
        return entity;
    }

    public static FaqCategoryDto fromEntity(FaqCategory entity) {
        if(entity == null) return null;
        FaqCategoryDto dto = new ModelMapper().map(entity, FaqCategoryDto.class);

//        List<Faq> faqs = entity.getFaqs();
//        if(faqs != null && !faqs.isEmpty()) {
//            for(Faq faq : faqs) {
//                dto.getFaqs().add(FaqDto.fromEntity(faq));
//            }
//        }
        return dto;
    }

    public static List<FaqCategoryDto> fromEntities(List<FaqCategory> entities) {
        if(entities == null) return null;
        List<FaqCategoryDto> dtos = new ArrayList<>();
        for(FaqCategory entity : entities) {
            FaqCategoryDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
