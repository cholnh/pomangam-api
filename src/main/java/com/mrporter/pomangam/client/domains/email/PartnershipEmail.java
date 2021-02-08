package com.mrporter.pomangam.client.domains.email;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "partnership_email_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartnershipEmail extends EntityAuditing {

    /**
     * 고객 이름
     */
    @Column(name = "client_name", nullable = false, length = 20)
    private String clientName;

    /**
     * 고객 받는 이메일
     */
    @Column(name = "client_email", nullable = false, length = 100)
    private String clientEmail;

    /**
     * 문의 내용
     */
    @Column(name = "contents", nullable = false, columnDefinition = "TEXT")
    private String contents;

    @Builder
    public PartnershipEmail(String clientName, String clientEmail, String contents) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.contents = contents;
    }
}
