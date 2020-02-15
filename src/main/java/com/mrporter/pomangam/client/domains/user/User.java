package com.mrporter.pomangam.client.domains.user;

import com.mrporter.pomangam._bases.utils.validation.annotation.Phone;
import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Entity
@Table(name = "user_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"deliveryDetailSite, fcmToken"})
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
     */
    @Column(name = "phone_number", unique = true, nullable = false)
    @Phone
    private String phoneNumber;

    /**
     * 비밀번호
     * 암호화되서 저장됨 (필수)
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 유저 실명
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 유저 별명
     * 한글, 영문 (특수문자 불가능, 따로 Validator 없음)
     */
    @Column(name = "nickname", nullable = false)
    private String nickname;

    /**
     * 성별
     * 남, 여 두 개 밖에 없음 (enum Gender)
     */
    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    /**
     * 생일
     */
    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    /**
     * 포인트
     * 현재 가용 포인트 (계산 되어 진 최종 포인트)
     * 포인트 변경은 서버 내부 로직에서만 변경 가능함. (외부 api 통해 변경 불가능, 관리자 제외)
     */
    @Column(name = "point", nullable = false)
    @PositiveOrZero
    private Integer point;

    /**
     * Fcm token
     * 읽기 전용
     */
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private FcmToken fcmToken;

    @PrePersist
    private void prePersist() {
        this.point = 0; // always 0 when its insert
    }

    @Builder
    public User(DeliveryDetailSite deliveryDetailSite, String phoneNumber, String password, String name, String nickname, Sex sex, LocalDate birth, @PositiveOrZero Integer point, FcmToken fcmToken, Boolean isActive) {
        super.setIsActive(isActive);
        this.deliveryDetailSite = deliveryDetailSite;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.sex = sex;
        this.birth = birth;
        this.point = point;
        this.fcmToken = fcmToken;
    }

    /**
     * 연관관계 편의 메서드
     * 순수객체까지 양방향 관계를 고려해야 함
     */
    public void applyFcmToken(final FcmToken fcmToken) {
        this.fcmToken = fcmToken;
    }
}
