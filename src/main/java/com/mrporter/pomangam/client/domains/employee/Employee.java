package com.mrporter.pomangam.client.domains.employee;

import com.mrporter.pomangam._bases.annotation.BooleanToYNConverter;
import com.mrporter.pomangam._bases.utils.validation.annotation.Phone;
import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.user.Sex;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Table(name = "employee_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Employee extends EntityAuditing {

    @Column(name = "customer_idx", nullable = true)
    private Integer jobIdx;

    @Column(name = "detail_site_idx", nullable = true)
    private Integer departmentIdx;

    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "phone_number", unique = true, nullable = false)
    @Phone
    private String phoneNumber;

    @Column(name = "email", nullable = true)
    @Email
    private String email;

    @Column(name = "is_active", nullable = true)
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isActive;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "commission_prc", nullable = false)
    private Integer commissionPrc;

    @Column(name = "commission_pct", nullable = false)
    private Short commissionPct;

    @Builder
    public Employee(Integer jobIdx, Integer departmentIdx, String id, String password, String name, Sex sex, LocalDate birth, String phoneNumber, @Email String email, Boolean isActive, LocalDate hireDate, Integer salary, Integer commissionPrc, Short commissionPct) {
        this.jobIdx = jobIdx;
        this.departmentIdx = departmentIdx;
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isActive = isActive;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commissionPrc = commissionPrc;
        this.commissionPct = commissionPct;
    }
}