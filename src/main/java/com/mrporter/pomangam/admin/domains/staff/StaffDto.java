package com.mrporter.pomangam.admin.domains.staff;

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
public class StaffDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private String id;
    @JsonIgnore
    private String password;
    private String name;
    private String phoneNumber;
    private Sex sex;
    private LocalDate birth;
    private Long idxFcmToken;

    public Staff toEntity() {
        Staff entity = new ModelMapper().map(this, Staff.class);
        entity.setPassword(Password.builder()
                .passwordValue(this.password)
                .build());
        return entity;
    }

    public static StaffDto fromEntity(Staff entity) {
        if(entity == null) return null;
        StaffDto dto = new StaffDto();
        dto.setIdx(entity.getIdx());
        dto.setRegisterDate(entity.getRegisterDate());
        dto.setModifyDate(entity.getModifyDate());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setSex(entity.getSex());
        dto.setBirth(entity.getBirth());
        dto.setIdxFcmToken(entity.getIdxFcmToken());
        dto.setPassword(null);
        return dto;
    }

    public static List<StaffDto> fromEntities(List<Staff> entities) {
        if(entities == null) return null;
        List<StaffDto> dtos = new ArrayList<>();
        for(Staff entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
