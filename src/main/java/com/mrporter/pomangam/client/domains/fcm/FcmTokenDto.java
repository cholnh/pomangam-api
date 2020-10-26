package com.mrporter.pomangam.client.domains.fcm;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FcmTokenDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String token;

    public FcmToken toEntity() {
        FcmToken entity = new ModelMapper().map(this, FcmToken.class);
        return entity;
    }

    public static FcmTokenDto fromEntity(FcmToken entity) {
        if(entity == null) return null;
        FcmTokenDto dto = new ModelMapper().map(entity, FcmTokenDto.class);
        return dto;
    }

    public static List<FcmTokenDto> fromEntities(List<FcmToken> entities) {
        if(entities == null) return null;
        List<FcmTokenDto> dtos = new ArrayList<>();
        for(FcmToken entity : entities) {
            FcmTokenDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Builder
    public FcmTokenDto(String token) {
        this.token = token;
    }
}
