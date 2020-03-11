package com.mrporter.pomangam.test.data.productReply;

import com.mrporter.pomangam.client.domains.product.reply.ProductReplyDto;
import com.mrporter.pomangam.client.services.product.reply.ProductReplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductReplyData {

    @Autowired
    ProductReplyServiceImpl productReplyService;

    @Transactional
    public void of(Long uIdx, Long pIdx, String contents, boolean isAnonymous) {
        ProductReplyDto reply = new ProductReplyDto();
        reply.setIdxUser(uIdx);
        reply.setIdxProduct(pIdx);
        reply.setContents(contents);
        reply.setIsAnonymous(isAnonymous);

        productReplyService.save(reply);
    }
}