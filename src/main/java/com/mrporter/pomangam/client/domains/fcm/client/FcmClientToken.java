package com.mrporter.pomangam.client.domains.fcm.client;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import com.mrporter.pomangam.client.domains.user.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "fcm_client_token_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmClientToken extends FcmToken {

    /**
     * User Id
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    @Builder
    public FcmClientToken(Long idx, String token, String phoneNumber) {
        super.setIdx(idx);
        super.setToken(token);
        this.phoneNumber = phoneNumber;
    }
}
