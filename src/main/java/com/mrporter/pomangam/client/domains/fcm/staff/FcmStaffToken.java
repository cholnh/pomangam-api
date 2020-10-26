package com.mrporter.pomangam.client.domains.fcm.staff;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fcm_staff_token_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmStaffToken extends FcmToken {

    @Column(name = "id_staff")
    private String idStaff;

    @Builder
    public FcmStaffToken(Long idx, String token, String idStaff) {
        super.setIdx(idx);
        super.setToken(token);
        this.idStaff = idStaff;
    }
}
