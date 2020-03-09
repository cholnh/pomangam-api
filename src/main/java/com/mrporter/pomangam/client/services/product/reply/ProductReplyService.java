package com.mrporter.pomangam.client.services.product.reply;

import com.mrporter.pomangam.client.domains.product.reply.ProductReplyDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductReplyService {
    List<ProductReplyDto> findByIdxProduct(Long pIdx, Long uIdx, Pageable pageable);
    ProductReplyDto findByIdx(Long idx, Long uIdx);
    long count();
    ProductReplyDto save(ProductReplyDto dto);
    ProductReplyDto update(ProductReplyDto dto);
    void delete(Long rIdx, Long idx);
}
