package com.mrporter.pomangam.client.domains.fcm;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.user.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "fcm_token_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"user"})
public class FcmToken extends EntityAuditing {

    /**
     * Fcm Token 값
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "token", nullable = true, length = 255)
    private String token;

    /**
     * User
     * 연관관계 주인
     */
    @JoinColumn(name = "id_user")
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private User user;

    @Builder
    public FcmToken(String token, User user) {
        this.token = token;
        if(user != null) {
            this.user = user;
        }
    }
}
