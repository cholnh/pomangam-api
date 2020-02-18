package com.mrporter.pomangam.client.services._bases;

public class ImagePath {

    public static String review(Long dIdx, Long sIdx, Long rIdx) {
        final String IMAGE_PATH_REVIEW = "images/dsites/" + dIdx + "/stores/" + sIdx + "/reviews/" + rIdx + "/";
        return IMAGE_PATH_REVIEW;
    }
}
