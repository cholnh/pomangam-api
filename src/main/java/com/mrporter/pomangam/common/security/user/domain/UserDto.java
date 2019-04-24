package com.mrporter.pomangam.common.security.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {

    private Integer idx;

    private Integer delivery_site_idx;

    private String id;

    private String pw;

    private String name;

    private String nickname;

    private Byte gender;

    private Short year_of_birth;

    private Byte month_of_birth;

    private Byte days_of_birth;

    private String phone_number;

    private Byte state_active;

    private Timestamp register_date;

    private Timestamp modify_date;

    private Integer point;

    private String authorities;

    public User toEntity() {
        return User.builder()
                .delivery_site_idx(delivery_site_idx)
                .id(id)
                .pw(pw)
                .name(name)
                .nickname(nickname)
                .gender(gender)
                .year_of_birth(year_of_birth)
                .month_of_birth(month_of_birth)
                .days_of_birth(days_of_birth)
                .phoneNumber(phone_number)
                .state_active(state_active)
                .register_date(register_date)
                .modify_date(modify_date)
                .point(point)
                .authorities(authorities)
                .build();

    }
}
