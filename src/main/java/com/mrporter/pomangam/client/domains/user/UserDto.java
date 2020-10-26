package com.mrporter.pomangam.client.domains.user;

import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSiteDto;
import com.mrporter.pomangam.client.domains.user.password.Password;
import com.mrporter.pomangam.client.domains.user.point.rank.PointRankDto;
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
public class UserDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private DeliveryDetailSiteDto deliveryDetailSite;
    private String phoneNumber;
    @JsonIgnore
    private String password;
    private String name;
    private String nickname;
    private Sex sex;
    private LocalDate birth;

    private PointRankDto userPointRank;
    private Integer userPoint;

    public User toEntity() {
        User entity = new ModelMapper().map(this, User.class);
        entity.setPassword(Password.builder()
                .passwordValue(this.password)
                .build());
        return entity;
    }

    public static UserDto fromEntity(User entity) {
        if(entity == null) return null;
        UserDto dto = new UserDto();
        dto.setIdx(entity.getIdx());
        dto.setRegisterDate(entity.getRegisterDate());
        dto.setModifyDate(entity.getModifyDate());
        dto.setDeliveryDetailSite(DeliveryDetailSiteDto.fromEntity(entity.getDeliveryDetailSite()));
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setName(entity.getName());
        dto.setNickname(entity.getNickname());
        dto.setSex(entity.getSex());
        dto.setBirth(entity.getBirth());
        dto.setPassword(null);
        dto.setUserPointRank(PointRankDto.fromEntity(entity.getPointRank()));
        return dto;
    }

    public static List<UserDto> fromEntities(List<User> entities) {
        if(entities == null) return null;
        List<UserDto> dtos = new ArrayList<>();
        for(User entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
