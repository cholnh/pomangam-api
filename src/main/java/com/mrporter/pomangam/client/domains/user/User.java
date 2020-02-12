package com.mrporter.pomangam.client.domains.user;

import com.mrporter.pomangam._bases.annotation.BooleanToYNConverter;
import com.mrporter.pomangam._bases.utils.validation.annotation.Phone;
import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Table(name = "user_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@ToString(exclude = {"deliveryDetailSite"})
@DynamicUpdate
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    /**
     * 상세 배달지
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_delivery_detail_site")
    private DeliveryDetailSite deliveryDetailSite;

    /**
     * 핸드폰 번호
     * ID 를 대체하여 사용됨.
     */
    @Column(name = "phone_number", unique = true)
    @Phone
    @NotBlank
    private String phoneNumber;

    /**
     * 비밀번호
     * 암호화되서 저장됨 (필수)
     */
    @NotBlank
    private String password;

    /**
     * 유저 실명
     */
    @NotBlank
    private String name;

    /**
     * 유저 별명
     * 한글, 영문 (특수문자 불가능, 따로 Validator 없음)
     */
    @NotBlank
    private String nickname;

    /**
     * 성별
     * 남, 여 두 개 밖에 없음 (enum Gender)
     */
    @Enumerated(EnumType.STRING)
    private Sex sex;

    /**
     * 생일
     */
    private Date birth;

    /**
     * 활성화 여부
     * 새로운 인스턴스 생성시, 초기화 값은 항상 false 로 고정되므로
     * builder 에서 .isActive(true) 추가 필요.
     */
    @Column(name = "is_active")
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isActive;

    /**
     * 등록 날짜
     */
    @Column(name = "register_date")
    @CreationTimestamp
    private LocalDateTime registerDate;

    /**
     * 수정 날짜
     */
    @Column(name = "modify_date")
    @UpdateTimestamp
    private LocalDateTime modifyDate;

    /**
     * 포인트
     * 현재 가용 포인트 (계산 되어 진 최종 포인트)
     * 포인트 변경은 서버 내부 로직에서만 변경 가능함. (외부 api 통해 변경 불가능, 관리자 제외)
     */
    @PositiveOrZero
    private Integer point;

    @PrePersist
    private void prePersist() {
        this.point = 0; // always 0 when its insert
        this.isActive = this.isActive == null ? true : this.isActive;
    }

    @Builder
    public User(DeliveryDetailSite deliveryDetailSite, @NotBlank String phoneNumber, @NotBlank String password, @NotBlank String name, String nickname, Sex sex, Date birth, Boolean isActive, LocalDateTime registerDate, LocalDateTime modifyDate, Integer point) {
        this.deliveryDetailSite = deliveryDetailSite;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.sex = sex;
        this.birth = birth;
        this.isActive = isActive;
        this.registerDate = registerDate;
        this.modifyDate = modifyDate;
        this.point = point;
    }
}
