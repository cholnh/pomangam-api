package com.mrporter.pomangam.client.services._bases;

public class ImagePath {

    /**
     * bases
     * C:/assets/images/_bases/
     */
    public static String bases() {
        return "images/_bases/";
    }

    /**
     * 배달지
     * C:/assets/images/dsites/{dIdx}/
     */
    public static String deliverySites(Long dIdx) {
        return "images/dsites/" + dIdx + "/";
    }

    /**
     * 배달지 상세
     * C:/assets/images/dsites/{dIdx}/details/{ddIdx}/
     */
    public static String details(Long dIdx, Long ddIdx) {
        return "images/dsites/" + dIdx + "/details/" + ddIdx + "/";
    }

    /**
     * 업체
     * C:/assets/images/dsites/{dIdx}/stores/{sIdx}/
     */
    public static String stores(Long dIdx, Long sIdx) {
        return "images/dsites/" + dIdx + "/stores/" + sIdx + "/";
    }

    /**
     * 제픔
     * C:/assets/images/dsites/{dIdx}/stores/{sIdx}/products/{pIdx}
     */
    public static String products(Long dIdx, Long sIdx, Long pIdx) {
        return "images/dsites/" + dIdx + "/stores/" + sIdx + "/products/" + pIdx + "/";
    }

    /**
     * 서브 제품
     * C:/assets/images/dsites/{dIdx}/stores/{sIdx}/subs/{subIdx}
     */
    public static String subs(Long dIdx, Long sIdx, Long subIdx) {
        return "images/dsites/" + dIdx + "/stores/" + sIdx + "/subs/" + subIdx + "/";
    }

    /**
     * 업체 리뷰
     * C:/assets/images/dsites/{dIdx}/stores/{sIdx}/reviews/{rIdx}/
     */
    public static String reviews(Long dIdx, Long sIdx, Long rIdx) {
        return "images/dsites/" + dIdx + "/stores/" + sIdx + "/reviews/" + rIdx + "/";
    }
}
