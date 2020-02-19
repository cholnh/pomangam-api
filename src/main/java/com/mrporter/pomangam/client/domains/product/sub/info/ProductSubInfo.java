package com.mrporter.pomangam.client.domains.product.sub.info;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 상속한 프록시 객체에서만 사용
@Embeddable
public class ProductSubInfo {

    /**
     * 서브 제품명
     * 글자수: utf8 기준 / 영문 20자 / 한글 20자
     */
    @Column(name = "name", nullable = false,  length = 20)
    private String name;

    /**
     * 서브 제품 설명
     * TEXT: 65535 Byte (64KB) / utf8 기준(3바이트 문자)으로 21844 글자 저장가능
     */
    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    /**
     * 서브 제품 부가설명
     * 글자수: utf8 기준 / 영문 255자 / 한글 255자
     */
    @Column(name = "sub_description", nullable = true, length = 255)
    private String subDescription;

    @Builder
    public ProductSubInfo(String name, String description, String subDescription) {
        this.name = name;
        this.description = description;
        this.subDescription = subDescription;
    }
}
