package com.mrporter.pomangam.store.domains.owner;

import com.mrporter.pomangam.client.domains.user.Sex;
import com.mrporter.pomangam.client.domains.user.password.Password;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OwnerDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String id;
    @JsonIgnore
    private String password;
    private Long idxStore;
    private String name;
    private String phoneNumber;
    private Sex sex;
    private LocalDate birth;
    private Long idxFcmToken;

    public Owner toEntity() {
        Owner entity = new ModelMapper().map(this, Owner.class);
        entity.setPassword(Password.builder()
                .passwordValue(this.password)
                .build());
        return entity;
    }

    public static OwnerDto fromEntity(Owner entity) {
        if(entity == null) return null;
        OwnerDto dto = new OwnerDto();
        dto.setIdx(entity.getIdx());
        dto.setRegisterDate(entity.getRegisterDate());
        dto.setModifyDate(entity.getModifyDate());
        dto.setId(entity.getId());
        dto.setIdxStore(entity.getIdxStore());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setSex(entity.getSex());
        dto.setBirth(entity.getBirth());
        dto.setIdxFcmToken(entity.getIdxFcmToken());
        dto.setPassword(null);
        return dto;
    }

    public static List<OwnerDto> fromEntities(List<Owner> entities) {
        if(entities == null) return null;
        List<OwnerDto> dtos = new ArrayList<>();
        for(Owner entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
