package com.mrporter.pomangam.test.data.productSub;

import com.mrporter.pomangam.client.domains.product.cost.Cost;
import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategory;
import com.mrporter.pomangam.client.domains.product.sub.image.ProductSubImage;
import com.mrporter.pomangam.client.domains.product.sub.image.ProductSubImageType;
import com.mrporter.pomangam.client.domains.product.sub.info.ProductSubInfo;
import com.mrporter.pomangam.client.repositories.product.sub.ProductSubJpaRepository;
import com.mrporter.pomangam.client.services._bases.ImagePath;
import com.mrporter.pomangam.test.data.productSubMapper.ProductSubMapperData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductSubData {

    @Autowired
    ProductSubJpaRepository productSubJpaRepository;
    @Autowired
    ProductSubMapperData productSubMapper;

    @Transactional
    public void of(Long idx, Long dIdx, Long sIdx, Long cIdx, String name, String desc, String sub, int sequence, int cost, Integer min, Integer max, List<Integer> imagePaths) {
        of(idx, dIdx, sIdx, cIdx, name, desc, sub, sequence, cost, min, max, imagePaths, null);
    }

    @Transactional
    public void of(Long idx, Long dIdx, Long sIdx, Long cIdx, String name, String desc, String sub, int sequence, int cost, Integer min, Integer max, List<Integer> imagePaths, Long pIdx) {
        ProductSub productSub = ProductSub.builder()
                .idx(0L)
                .productSubInfo(ProductSubInfo.builder()
                        .name(name)
                        .description(desc)
                        .subDescription(sub)
                        .build())
                .idxStore(sIdx)
                .productSubCategory(ProductSubCategory.builder().idx(cIdx).build())
                .numberMinimum(min)
                .numberMaximum(max)
                .sequence(sequence)
                .cost(Cost.builder()
                        .unitCost(cost)
                        .build())
                .build();

        // image
        for(int i=0; i<imagePaths.size(); i++) {
            ProductSubImage productSubImage = ProductSubImage.builder()
                    .imagePath(ImagePath.subs(sIdx, idx) + (imagePaths.get(i)) + ".jpg")
                    .imageType(i==0 ? ProductSubImageType.MAIN : ProductSubImageType.SUB)
                    .sequence(i+1)
                    .build();
            productSub.addImage(productSubImage);
        }

        ProductSub entity = productSubJpaRepository.save(productSub);

        if(pIdx != null) {
            productSubMapper.of(pIdx, entity.getIdx());
        }
    }

    @Transactional
    public void of(Long idx, Long dIdx, Long sIdx, Long cIdx, String name, String desc, String sub, int sequence, int cost, Integer min, Integer max, int imagePathIdxSub, Long pIdx) {
        ProductSub productSub = ProductSub.builder()
                .idx(0L)
                .productSubInfo(ProductSubInfo.builder()
                        .name(name)
                        .description(desc)
                        .subDescription(sub)
                        .build())
                .idxStore(sIdx)
                .productSubCategory(ProductSubCategory.builder().idx(cIdx).build())
                .numberMinimum(min)
                .numberMaximum(max)
                .sequence(sequence)
                .cost(Cost.builder()
                        .unitCost(cost)
                        .build())
                .build();

        // image
        ProductSubImage productSubImage = ProductSubImage.builder()
                .imagePath("assets/images/stores/" + sIdx + "/subs/" + imagePathIdxSub + "/" + 1 + ".jpg")
                .imageType(ProductSubImageType.MAIN)
                .sequence(1)
                .build();
        productSub.addImage(productSubImage);

        ProductSub entity = productSubJpaRepository.save(productSub);

        if(pIdx != null) {
            productSubMapper.of(pIdx, entity.getIdx());
        }
    }
}