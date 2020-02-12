package com.mrporter.pomangam.client.domains._bases;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "common_map_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class CommonMap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "\"key\"")
    private String key;

    @Column(name = "\"value\"")
    private String value;

    @Builder
    public CommonMap(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
