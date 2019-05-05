package com.mrporter.pomangam.customerService.faq.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "faq_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class Faq implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String title;

    private String contents;

    private Timestamp register_date;

    @Builder
    public Faq(String title, String contents, Timestamp register_date) {
        this.title = title;
        this.contents = contents;
        this.register_date = register_date;
    }
}
