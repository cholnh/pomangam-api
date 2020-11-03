package com.mrporter.pomangam.test.data.product;

import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.product.ProductType;
import com.mrporter.pomangam.client.domains.product.category.ProductCategory;
import com.mrporter.pomangam.client.domains.product.cost.Cost;
import com.mrporter.pomangam.client.domains.product.image.ProductImage;
import com.mrporter.pomangam.client.domains.product.image.ProductImageType;
import com.mrporter.pomangam.client.domains.product.info.ProductInfo;
import com.mrporter.pomangam.client.repositories.product.ProductJpaRepository;
import com.mrporter.pomangam.client.repositories.product.sub.category.ProductSubCategoryJpaRepository;
import com.mrporter.pomangam.client.services._bases.ImagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductData {

    @Autowired
    ProductJpaRepository productJpaRepository;

    @Autowired
    ProductSubCategoryJpaRepository productSubCategoryJpaRepository;

    @Transactional
    public void of(Long idx, String name, ProductType productType, Long sIdx, Long cIdx, int seq, int unitCost, int cf, int sf, int ...idxesImage) {
        Product product = Product.builder()
                .productInfo(ProductInfo.builder()
                        .name(name)
                        .description(name+" 설명 부분입니다.")
                        .subDescription(name+" 부가설명 부분입니다.")
                        .build())
                .idxStore(sIdx)
                .productCategory(ProductCategory.builder().idx(cIdx).build())
                .sequence(seq)
                .cost(Cost.builder()
                        .unitCost(unitCost)
                        .priceClientFee(cf)
                        .priceStoreFee(sf)
                        .build())
                .productType(productType)
                .build();

        // image
        for(int i=0; i<idxesImage.length; i++) {
            int idxImage = idxesImage[i];
            ProductImage productImage = ProductImage.builder()
                    .imagePath(ImagePath.products(sIdx, idx)+idxImage+".jpg")
                    .imageType(i==0 ? ProductImageType.MAIN : ProductImageType.SUB)
                    .sequence(i+1)
                    .build();
            product.addImage(productImage);
        }

        productJpaRepository.save(product);
    }
}