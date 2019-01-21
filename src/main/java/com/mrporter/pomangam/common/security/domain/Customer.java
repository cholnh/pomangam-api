package com.mrporter.pomangam.common.security.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "customer_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Integer point;

    @Builder
    public Customer(Integer delivery_site_idx,
                    String id,
                    String pw,
                    String name,
                    String nickname,
                    Integer gender,
                    Integer year_of_birth,
                    Integer month_of_birth,
                    Integer days_of_birth,
                    String phone_number,
                    Integer status,
                    Date register_date,
                    Date modify_date,
                    Integer point) {
        this.delivery_site_idx = delivery_site_idx;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.year_of_birth = year_of_birth;
        this.month_of_birth = month_of_birth;
        this.days_of_birth = days_of_birth;
        this.phone_number = phone_number;
        this.status = status;
        this.register_date = register_date;
        this.modify_date = modify_date;
        this.point = point;
    }
}
