package com.mrporter.pomangam.client.domains.fcm.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FcmClientTokenDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private String token;
    private String phoneNumber;

    public FcmClientToken toEntity() {
        FcmClientToken entity = new ModelMapper().map(this, FcmClientToken.class);
        if(phoneNumber != null) {
            entity.setPhoneNumber(phoneNumber);
        }
        return entity;
    }

    public static FcmClientTokenDto fromEntity(FcmClientToken entity) {
        if(entity == null) return null;
        FcmClientTokenDto dto = new ModelMapper().map(entity, FcmClientTokenDto.class);
        if(entity.getPhoneNumber() != null) {
            dto.setPhoneNumber(entity.getPhoneNumber());
        }
        return dto;
    }

    public static List<FcmClientTokenDto> fromEntities(List<FcmClientToken> entities) {
        if(entities == null) return null;
        List<FcmClientTokenDto> dtos = new ArrayList<>();
        for(FcmClientToken entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}