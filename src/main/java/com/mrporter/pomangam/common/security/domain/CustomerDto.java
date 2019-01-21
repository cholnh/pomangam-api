package com.mrporter.pomangam.common.security.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
public class CustomerDto implements Serializable {

    private Long idx;

    private Integer delivery_site_idx;

    private String id;

    private String pw;

    private String name;

    private String nickname;

    private Integer gender;

    private Integer year_of_birth;

    private Integer month_of_birth;

    private Integer days_of_birth;

    private String phone_number;

    private Integer status;

    private Date register_date;

    private Date modify_date;

    private Integer point; //


    public Customer toEntity() {
        return Customer.builder()
                .delivery_site_idx(delivery_site_idx)
                .id(id)
                .pw(pw)
                .name(name)
                .nickname(nickname)
                .gender(gender)
                .year_of_birth(year_of_birth)
                .month_of_birth(month_of_birth)
                .days_of_birth(days_of_birth)
                .phone_number(phone_number)
                .status(status)
                .register_date(register_date)
                .modify_date(modify_date)
                .point(point)
                .build();
    }
}
