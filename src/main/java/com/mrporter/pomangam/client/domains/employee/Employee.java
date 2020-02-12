package com.mrporter.pomangam.client.domains.employee;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "employee_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "customer_idx")
    private Integer jobIdx;

    @Column(name = "detail_site_idx")
    private Integer departmentIdx;

    @Column(name = "id")
    private String id;

    @Column(name = "pw")
    private String pw;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Byte gender;

    @Column(name = "year_of_birth")
    private Short yearOfBirth;

    @Column(name = "month_of_birth")
    private Byte monthOfBirth;

    @Column(name = "days_of_birth")
    private Byte daysOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "state_active")
    private Byte stateActive;

    @Column(name = "hire_date")
    private Timestamp hireDate;

    @Column(name = "register_date")
    private Timestamp registerDate;

    @Column(name = "modify_date")
    private Timestamp modifyDate;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "commission_prc")
    private Integer commissionPrc;

    @Column(name = "commission_pct")
    private Short commissionPct;

    @Builder
    public Employee(Integer jobIdx, Integer departmentIdx, String id, String pw, String name, Byte gender, Short yearOfBirth, Byte monthOfBirth, Byte daysOfBirth, String phoneNumber, String email, Byte stateActive, Timestamp hireDate, Timestamp registerDate, Timestamp modifyDate, Integer salary, Integer commissionPrc, Short commissionPct) {
        this.jobIdx = jobIdx;
        this.departmentIdx = departmentIdx;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.daysOfBirth = daysOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.stateActive = stateActive;
        this.hireDate = hireDate;
        this.registerDate = registerDate;
        this.modifyDate = modifyDate;
        this.salary = salary;
        this.commissionPrc = commissionPrc;
        this.commissionPct = commissionPct;
    }
}