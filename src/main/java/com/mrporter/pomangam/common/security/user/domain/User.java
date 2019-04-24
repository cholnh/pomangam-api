package com.mrporter.pomangam.common.security.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "user_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer delivery_site_idx;

    @NotBlank
    @Column(name = "id")
    private String id;

    @NotBlank
    private String pw;

    @NotBlank
    private String name;

    private String nickname;

    private Byte gender;

    private Short year_of_birth;

    private Byte month_of_birth;

    private Byte days_of_birth;

    @Column(name = "phone_number")
    private String phoneNumber;

    private Byte state_active;

    private Timestamp register_date;

    private Timestamp modify_date;

    private Integer point;

    private String authorities;

    @Builder
    public User(Integer delivery_site_idx, String id, String pw, String name, String nickname, Byte gender, Short year_of_birth, Byte month_of_birth, Byte days_of_birth, String phoneNumber, Byte state_active, Timestamp register_date, Timestamp modify_date, Integer point, String authorities) {
        this.delivery_site_idx = delivery_site_idx;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.year_of_birth = year_of_birth;
        this.month_of_birth = month_of_birth;
        this.days_of_birth = days_of_birth;
        this.phoneNumber = phoneNumber;
        this.state_active = state_active;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.point = point;
        this.authorities = authorities;
    }
}
