package com.mrporter.pomangam.client.domains.product.info;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 상속한 프록시 객체에서만 사용
@Embeddable
public class ProductInfo {

    /**
     * 제품명
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "name", nullable = false,  length = 20)
    private String name;

    /**
     * 제품 설명
     * TEXT: 65535 Byte (64KB) / utf8 기준(3바이트 문자)으로 21844 글자 저장가능
     */
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * 제품 부가 설명
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "sub_description", nullable = false, length = 255)
    private String subDescription;

    @Builder
    public ProductInfo(String name, String description, String subDescription) {
        this.name = name;
        this.description = description;
        this.subDescription = subDescription;
    }
}
