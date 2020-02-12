package com.mrporter.pomangam.admin.domains.user;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySiteDto;
import com.mrporter.pomangam.client.domains.user.Sex;
import com.mrporter.pomangam.client.domains.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class _UserDto implements Serializable {

    private Integer idx;

    private DeliverySiteDto deliverySiteDto;

    private String phoneNumber;

    private String pw;

    private String name;

    private String nickname;

    private Sex gender;

    private Date birth;

    private boolean isActive;

    private Timestamp registerDate;

    private Timestamp modifyDate;

    private Integer point;

    public User toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, User.class);
    }

    public static _UserDto fromEntity(User entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, _UserDto.class);
    }

    public static List<_UserDto> fromEntities(List<User> entities) {
        List<_UserDto> dtos = new ArrayList<>();
        for(User entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
