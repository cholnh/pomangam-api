package com.mrporter.pomangam.client.domains.fcm.owner;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fcm_owner_token_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmOwnerToken extends FcmToken {

    @Column(name = "id_owner")
    private String idOwner;

    @Builder
    public FcmOwnerToken(Long idx, String token, String idOwner) {
        super.setIdx(idx);
        super.setToken(token);
        this.idOwner = idOwner;
    }
}
