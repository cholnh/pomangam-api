package com.mrporter.pomangam.common.security.authority.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Deprecated
@Table(name = "authority_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @NotBlank
    @Column(name = "user_id")
    private String userId;

    @Column(name = "authorities")
    private String authorities;

    @Builder
    public Authority(@NotBlank String userId, String authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }
}
