package com.mrporter.pomangam.client.domains.store.schedule;

import com.mrporter.pomangam._bases.annotation.BooleanToYNConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import java.time.LocalTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 상속한 프록시 객체에서만 사용
@Embeddable
public class StoreSchedule {

    /**
     * 영업 시작 시간
     */
    @Column(name = "open_time", nullable = false)
    private LocalTime openTime;

    /**
     * 영업 종료 시간
     */
    @Column(name = "close_time", nullable = false)
    private LocalTime closeTime;

    /**
     * 오프닝(휴일 포함) 여부 (Y/N)
     * default: true(Y)
     * 대문자 필수
     *
     * true: 영업 개시
     * false: 영업 일시 정지
     */
    @Column(name = "is_opening", nullable = false, length = 1)
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isOpening;

    /**
     * 정지 사유
     * 영업 일시 정지(isOpening-false) 상태일 때 View 단에 표현됨.
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "pause_description", nullable = true, length = 255)
    private String pauseDescription;

    @PrePersist
    private void prePersist() {
        // Todo: "휴무" -> Globalization 상수 처리
        this.pauseDescription = pauseDescription == null ? "휴무" : pauseDescription;
    }

    @Builder
    public StoreSchedule(LocalTime openTime, LocalTime closeTime, Boolean isOpening, String pauseDescription) {
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.isOpening = isOpening;
        this.pauseDescription = pauseDescription;
    }
}
