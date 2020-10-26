package com.mrporter.pomangam.client.domains.fcm.owner;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FcmOwnerTokenDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String token;
    private String id;

    public FcmOwnerToken toEntity() {
        FcmOwnerToken entity = new ModelMapper().map(this, FcmOwnerToken.class);
        if(id != null) {
            entity.setIdOwner(id);
        }
        return entity;
    }

    public static FcmOwnerTokenDto fromEntity(FcmOwnerToken entity) {
        if(entity == null) return null;
        FcmOwnerTokenDto dto = new ModelMapper().map(entity, FcmOwnerTokenDto.class);
        dto.setId(entity.getIdOwner());
        return dto;
    }

    public static List<FcmOwnerTokenDto> fromEntities(List<FcmOwnerToken> entities) {
        if(entities == null) return null;
        List<FcmOwnerTokenDto> dtos = new ArrayList<>();
        for(FcmOwnerToken entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}