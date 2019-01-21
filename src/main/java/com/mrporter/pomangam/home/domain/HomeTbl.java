package com.mrporter.pomangam.home.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
//@Entity
public class HomeTbl implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;
    String name;
    String desc;

    @Builder
    public HomeTbl(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
