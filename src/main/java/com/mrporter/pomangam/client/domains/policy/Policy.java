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

    @Column(name = "policy_name", nullable = false)
    private String policyName;

    @Column(name = "policy_contents", nullable = false)
    private String policyContents;

    @Builder
    public Policy(String policyName, String policyContents) {
        this.policyName = policyName;
        this.policyContents = policyContents;
    }
}
