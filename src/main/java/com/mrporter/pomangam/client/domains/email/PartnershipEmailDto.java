package com.mrporter.pomangam.client.domains.email;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PartnershipEmailDto {
    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String clientName;
    private String clientEmail;
    private String contents;

    public PartnershipEmail toEntity() {
        PartnershipEmail entity = new ModelMapper().map(this, PartnershipEmail.class);
        return entity;
    }

    public static PartnershipEmailDto fromEntity(PartnershipEmail entity) {
        PartnershipEmailDto dto = new ModelMapper().map(entity, PartnershipEmailDto.class);
        return dto;
    }

    public static List<PartnershipEmailDto> fromEntities(List<PartnershipEmail> entities) {
        List<PartnershipEmailDto> dtos = new ArrayList<>();
        for(PartnershipEmail entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
