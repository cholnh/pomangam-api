package com.mrporter.pomangam.client.domains.map;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "common_map_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class CommonMap extends EntityAuditing {

    /**
     * Key
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "common_key", nullable = false, length = 255)
    private String key;

    /**
     * Value
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "common_value", nullable = false, length = 255)
    private String value;

    @Builder
    public CommonMap(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
