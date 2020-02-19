package com.mrporter.pomangam.client.domains.store.info;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 상속한 프록시 객체에서만 사용
@Embeddable
public class StoreInfo {

    /**
     * 업체명
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    /**
     * 업체 설명
     * TEXT: 65535 Byte (64KB) / utf8 기준(3바이트 문자)으로 21844 글자 저장가능
     */
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * 업체 부가 설명
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "sub_description", nullable = true, length = 255)
    private String subDescription;

    /**
     * 업주 이름
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "owner_name", nullable = true, length = 20)
    private String ownerName;

    /**
     * 업체 사업장 명
     * 글자수: utf8 기준 / 영문 50자 / 한글 50자
     */
    @Column(name = "company_name", nullable = false, length = 50)
    private String companyName;

    /**
     * 업체 사업장 위치
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "company_location", nullable = true, length = 255)
    private String companyLocation;

    /**
     * 업체 사업장 전화번호
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "company_phone_number", nullable = true, length = 20)
    private String companyPhoneNumber;

    @PrePersist
    private void prePersist() {
        this.companyName = companyName == null ? this.name : companyName;
    }

    @Builder
    public StoreInfo(String name, String description, String subDescription, String ownerName, String companyName, String companyLocation, String companyPhoneNumber) {
        this.name = name;
        this.description = description;
        this.subDescription = subDescription;
        this.ownerName = ownerName;
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.companyPhoneNumber = companyPhoneNumber;
    }
}
