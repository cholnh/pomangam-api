package com.mrporter.pomangam.client.domains.user.password;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.PositiveOrZero;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 상속한 프록시 객체에서만 사용
public class Password {

    /**
     * 암호화 된 패스워드
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "password_value", nullable = false, length = 255)
    private String passwordValue;

    /**
     * 패스워드 비교 실패 횟수
     */
    @Column(name = "password_failed_count", nullable = false, columnDefinition = "INT default 0")
    @PositiveOrZero
    private int failedCount;

    @Builder
    public Password(String passwordValue, @PositiveOrZero int failedCount) {
        this.passwordValue = passwordValue;
        this.failedCount = failedCount;
    }
}
