package com.mrporter.pomangam._bases.securities.guest.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

//@Table(name = "guest_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
//@Entity
public class Guest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "register_date")
    private Timestamp registerDate;

    @Builder
    public Guest(Timestamp registerDate) {
        this.registerDate = registerDate;
    }
}
