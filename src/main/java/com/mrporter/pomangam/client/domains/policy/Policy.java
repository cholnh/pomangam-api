package com.mrporter.pomangam.client.domains.policy;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "policy_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Policy extends EntityAuditing {

    /**
     * 규정 이름
     * 글자수: utf8 기준 / 영문 100자 / 한글 100자
     */
    @Column(name = "policy_name", nullable = false, length = 100)
    private String policyName;

    /**
     * 규정 내용
     * TEXT: 65535 Byte (64KB) / utf8 기준(3바이트 문자)으로 21844 글자 저장가능
     */
    @Column(name = "policy_contents", nullable = false, columnDefinition = "TEXT")
    private String policyContents;

    @Builder
    public Policy(String policyName, String policyContents) {
        this.policyName = policyName;
        this.policyContents = policyContents;
    }
}
