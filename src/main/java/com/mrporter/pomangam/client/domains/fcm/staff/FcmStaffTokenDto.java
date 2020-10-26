package com.mrporter.pomangam.client.domains.fcm.staff;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FcmStaffTokenDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String token;
    private String id;

    public FcmStaffToken toEntity() {
        FcmStaffToken entity = new ModelMapper().map(this, FcmStaffToken.class);
        entity.setIdStaff(id);
        return entity;
    }

    public static FcmStaffTokenDto fromEntity(FcmStaffToken entity) {
        if(entity == null) return null;
        FcmStaffTokenDto dto = new ModelMapper().map(entity, FcmStaffTokenDto.class);
        dto.setId(entity.getIdStaff());
        return dto;
    }

    public static List<FcmStaffTokenDto> fromEntities(List<FcmStaffToken> entities) {
        if(entities == null) return null;
        List<FcmStaffTokenDto> dtos = new ArrayList<>();
        for(FcmStaffToken entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}