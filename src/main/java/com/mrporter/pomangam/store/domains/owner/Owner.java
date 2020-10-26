package com.mrporter.pomangam.store.domains.owner;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.user.Sex;
import com.mrporter.pomangam.client.domains.user.password.Password;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "owner_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends EntityAuditing {

    /**
     * 아이디
     * 글자수: utf8 기준 / 영문 15자
     */
    @Column(name = "id", unique = true, nullable = false, length = 15)
    private String id;

    /**
     * 비밀번호
     * 암호화되서 저장됨 (필수)
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "password", nullable = false, length = 255)
    private Password password;

    /**
     * 담당 업체 인덱스
     */
    @Column(name = "idx_store", nullable = false)
    private Long idxStore;

    /**
     * 업주 실명
     * 글자수: utf8 기준 / 영문 30자 / 한글 30자
     */
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    /**
     * 업주 휴대전화 번호
     */
    @Column(name = "phone_number", nullable = true, length = 20)
    private String phoneNumber;

    /**
     * 성별
     * 남, 여 두 개 밖에 없음 (enum Gender)
     */
    @Column(name = "sex", nullable = false, length = 6)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    /**
     * 생일
     */
    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    /**
     * Fcm token 인덱스
     */
    @Column(name = "idx_fcm_token", nullable = false)
    private Long idxFcmToken;

    /**
     * 유저 권한
     *
     * Prefix: "ROLE_"
     * Delimiter: 쉼표(,)
     * Client 기본값: "ROLE_USER"
     * Store Owner 기본값: "ROLE_STORE_OWNER"
     * 예시: ROLE_USER,ROLE_STORE_OWNER
     * 글자수: utf8 기준 / 영문 256자 / 한글 256자
     */
    @Column(name = "authorities", nullable = false, length = 256)
    private String authorities;

    @PrePersist
    private void prePersist() {
        this.authorities = authorities == null
                ? "ROLE_STORE_OWNER"
                : isValidAuthorities(authorities)
                    ? authorities
                    : "ROLE_STORE_OWNER";
    }

    @Builder
    public Owner(Long idx, Boolean isActive, String id, Password password, Long idxStore, String name, String phoneNumber, Sex sex, LocalDate birth, Long idxFcmToken, String authorities) {
        super.setIdx(idx);
        super.setIsActive(isActive);
        this.id = id;
        this.password = password;
        this.idxStore = idxStore;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.birth = birth;
        this.idxFcmToken = idxFcmToken;
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return this.authorities.split(",");
    }

    public void addAuthority(String authority) {
        if(this.authorities == null || this.authorities.isEmpty()) {
            this.authorities = authority;
        } else {
            this.authorities += "," + authority;
        }
    }

    private boolean isValidAuthorities(String authorities) {
        try {
            if(authorities == null || authorities.isEmpty()) return false;
            for(String authority : getAuthorities()) {
                if(authority.length() < 6 ||
                        !authority.toUpperCase().substring(0, 5).equals("ROLE_")) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
