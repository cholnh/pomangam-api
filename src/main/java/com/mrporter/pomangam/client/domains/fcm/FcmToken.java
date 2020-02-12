package com.mrporter.pomangam.client.domains.fcm;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "fcm_token_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class FcmToken implements Serializable {

    @Id
    private String token;

    private Integer delivery_site_idx;

    private Integer guest_idx;

    private String customer_id;

    private String owner_id;

    private Byte state;

    private Timestamp register_date;

    @Builder
    public FcmToken(String token, Integer delivery_site_idx, Integer guest_idx, String customer_id, String owner_id, Byte state, Timestamp register_date) {
        this.token = token;
        this.delivery_site_idx = delivery_site_idx;
        this.guest_idx = guest_idx;
        this.customer_id = customer_id;
        this.owner_id = owner_id;
        this.state = state;
        this.register_date = register_date;
    }
}
