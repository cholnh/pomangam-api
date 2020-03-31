package com.mrporter.pomangam.client.domains.user;

import com.mrporter.pomangam._bases.utils.validation.annotation.Phone;
import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.user.password.Password;
import com.mrporter.pomangam.client.domains.user.point.rank.PointRank;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"deliveryDetailSite"})
public class User extends EntityAuditing {

    /**
     * 상세 배달지
     * 연관관계 주인
     */
    @JoinColumn(name = "idx_delivery_detail_site")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DeliveryDetailSite deliveryDetailSite;

    /**
     * 핸드폰 번호
     * ID 를 대체하여 사용됨.
     * 글자수: utf8 기준 / 영문 15자 / 한글 15자
     */
    @Column(name = "phone_number", unique = true, nullable = false, length = 15)
    @Phone
    private String phoneNumber;

    /**
     * 비밀번호
     * 암호화되서 저장됨 (필수)
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "password", nullable = false, length = 255)
    private Password password;

    /**
     * 유저 실명
     * 글자수: utf8 기준 / 영문 30자 / 한글 30자
     */
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    /**
     * 유저 별명
     * 한글, 영문 (특수문자 불가능, 따로 Validator 없음)
     * 글자수: utf8 기준 / 영문 15자 / 한글 15자
     */
    @Column(name = "nickname", nullable = false, length = 15)
    private String nickname;

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
     * 포인트 (Deprecated - PointLog 로 대체)
     * 현재 가용 포인트 (계산 되어 진 최종 포인트)
     * 포인트 변경은 서버 내부 로직에서만 변경 가능함. (외부 api 통해 변경 불가능, 관리자 제외)
     */
//    @Deprecated
//    @Column(name = "point", nullable = false, columnDefinition = "INT default 0")
//    @PositiveOrZero
//    private Integer point;

    /**
     * Fcm token 인덱스
     */
    @Column(name = "idx_fcm_token", nullable = false)
    private Long idxFcmToken;

    /**
     * 포인트 계급
     */
    @JoinColumn(name = "idx_point_rank")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PointRank pointRank;

    /**
     * 쿠폰
     */
//    @JoinColumn(name = "idx_user", nullable = true)
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Coupon> coupons;

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
                ? "ROLE_USER"
                : isValidAuthorities(authorities)
                    ? authorities
                    : "ROLE_USER";
    }

    @Builder
    public User(Long idx, Boolean isActive, DeliveryDetailSite deliveryDetailSite, String phoneNumber, Password password, String name, String nickname, Sex sex, LocalDate birth, Long idxFcmToken, PointRank pointRank, String authorities) {
        super.setIdx(idx);
        super.setIsActive(isActive);
        this.deliveryDetailSite = deliveryDetailSite;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.sex = sex;
        this.birth = birth;
        this.idxFcmToken = idxFcmToken;
        this.pointRank = pointRank;
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
