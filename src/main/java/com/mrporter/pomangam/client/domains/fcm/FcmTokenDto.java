package com.mrporter.pomangam.client.domains.fcm;

import com.mrporter.pomangam.client.domains.user.User;
import lombok.*;
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
    private String phoneNumber;

    public FcmToken toEntity() {
        FcmToken entity = new ModelMapper().map(this, FcmToken.class);
        User user = User.builder()
                .phoneNumber(phoneNumber)
                .build();
        entity.setUser(user);
        return entity;
    }

    public static FcmTokenDto fromEntity(FcmToken entity) {
        if(entity == null) return null;
        FcmTokenDto dto = new ModelMapper().map(entity, FcmTokenDto.class);
        dto.setPhoneNumber(entity.getUser().getPhoneNumber());
        return dto;
    }

    public static List<FcmTokenDto> fromEntities(List<FcmToken> entities) {
        if(entities == null) return null;
        List<FcmTokenDto> dtos = new ArrayList<>();
        for(FcmToken entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}