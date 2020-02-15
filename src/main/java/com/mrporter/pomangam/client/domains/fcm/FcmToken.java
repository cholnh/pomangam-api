package com.mrporter.pomangam.client.domains.fcm;

import com.mrporter.pomangam._bases.annotation.BooleanToYNConverter;
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
     */
    @Column(name = "token", nullable = true)
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

    /**
     * 연관관계 편의 메서드
     * 순수객체까지 양방향 관계를 고려해야 함
     * User 쪽에도 편의 메서드 생성해야 함
     */
    public void applyUser(final User user) {
        this.user = user;
        user.applyFcmToken(this);
    }

}
