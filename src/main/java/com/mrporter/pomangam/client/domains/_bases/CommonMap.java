package com.mrporter.pomangam.client.domains._bases;

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

    @Column(name = "key", nullable = false)
    private String key;

    @Column(name = "value", nullable = false)
    private String value;

    @Builder
    public CommonMap(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
