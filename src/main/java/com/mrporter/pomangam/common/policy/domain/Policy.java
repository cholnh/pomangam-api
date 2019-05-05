package com.mrporter.pomangam.common.policy.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "policy_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class Policy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "policy_name")
    private String policyName;

    @Column(name = "policy_contents")
    private String policyContents;

    @Builder
    public Policy(String policyName, String policyContents) {
        this.policyName = policyName;
        this.policyContents = policyContents;
    }
}
