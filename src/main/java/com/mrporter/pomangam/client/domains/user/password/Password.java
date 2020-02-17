package com.mrporter.pomangam.client.domains.user.password;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Embeddable
@AllArgsConstructor
public class Password {

    PasswordEncoder passwordEncoder;

    /**
     * 암호화 된 패스워드
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "password", nullable = false, length = 255)
    private String value;

    /**
     * 아직 미사용.
     */
    @Column(name = "password_expiration_date")
    private LocalDateTime expirationDate;

    /**
     * 패스워드 비교 실패 횟수
     */
    @Column(name = "password_failed_count", nullable = false)
    private int failedCount;

    @Column(name = "password_ttl")
    private long ttl;

    @Builder
    public Password(final String value) {
        this.ttl = 1209_604; // 1209_604 is 14 days
        this.value = encodePassword(value);
        this.expirationDate = extendExpirationDate();
    }

    public boolean isMatched(final String rawPassword) throws PasswordFailedExceededException {
        if (failedCount >= 5)
            throw new PasswordFailedExceededException();

        final boolean matches = passwordEncoder.matches(rawPassword, value);
        updateFailedCount(matches);
        return matches;
    }

    public void changePassword(final String newPassword, final String oldPassword) throws Exception {
        if (isMatched(oldPassword)) {
            value = encodePassword(newPassword);
            extendExpirationDate();
        }
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    private LocalDateTime extendExpirationDate() {
        return LocalDateTime.now().plusDays(ttl);
    }

    public void updateFailedCount(boolean matches) {
        if(matches) {
            this.failedCount = 0;
        } else {
            this.failedCount++;
        }
    }
}
