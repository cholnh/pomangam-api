package com.mrporter.pomangam.client.domains.fcm;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class FcmToken extends EntityAuditing implements Serializable {

    /**
     * Fcm Token 값
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "token", nullable = true, length = 255)
    private String token;

}
