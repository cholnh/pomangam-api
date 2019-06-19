package com.mrporter.pomangam.common.fcm.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "fcm_token_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class FcmToken implements Serializable {

    @Id
    private String token;

    @Builder
    public FcmToken(String token) {
        this.token = token;
    }
}
