package com.mrporter.pomangam.client.services._bases;

public class ImagePath {

    /**
     * bases
     * C:/assets/images/_bases/
     */
    public static String bases() {
        return "assets/images/_bases/";
    }

    /**
     * 배달지
     * C:/assets/images/dsites/{dIdx}/
     */
    public static String deliverySites(Long dIdx) {
        return "assets/images/dsites/" + dIdx + "/";
    }

    /**
     * 배달지 상세
     * C:/assets/images/dsites/{dIdx}/details/{ddIdx}/
     */
    public static String details(Long dIdx, Long ddIdx) {
        return "assets/images/dsites/" + dIdx + "/details/" + ddIdx + "/";
    }

    /**
     * 업체
     * C:/assets/images/stores/{sIdx}/
     */
    public static String stores(Long sIdx) {
        return "assets/images/stores/" + sIdx + "/";
    }

    /**
     * 제픔
     * C:/assets/images/stores/{sIdx}/products/{pIdx}
     */
    public static String products(Long sIdx, Long pIdx) {
        return "assets/images/stores/" + sIdx + "/products/" + pIdx + "/";
    }

    /**
     * 서브 제품
     * C:/assets/images/stores/{sIdx}/subs/{subIdx}
     */
    public static String subs(Long sIdx, Long subIdx) {
        return "assets/images/stores/" + sIdx + "/subs/" + subIdx + "/";
    }

    /**
     * 업체 리뷰
     * C:/assets/images/stores/{sIdx}/reviews/{rIdx}/
     */
    public static String reviews(Long sIdx, Long rIdx) {
        return "assets/images/stores/" + sIdx + "/reviews/" + rIdx + "/";
    }

    /**
     * 광고
     * C:/assets/images/dsites/{dIdx}/advertisements/{adIdx}/
     */
    public static String advertisements(Long dIdx, Long adIdx) {
        return "assets/images/dsites/" + dIdx + "/advertisements/" + adIdx + "/";
    }
}
