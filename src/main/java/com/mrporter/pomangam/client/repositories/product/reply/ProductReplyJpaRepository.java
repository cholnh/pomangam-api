package com.mrporter.pomangam.client.repositories.product.reply;

import com.mrporter.pomangam.client.domains.product.reply.ProductReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface ProductReplyJpaRepository extends JpaRepository<ProductReply, Long>, ProductReplyCustomRepository {
    Page<ProductReply> findByIdxProductAndIsActiveIsTrue(Long idxProduct, Pageable pageable);
    ProductReply findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface ProductReplyCustomRepository {

}

@Transactional(readOnly = true)
class ProductReplyCustomRepositoryImpl extends QuerydslRepositorySupport implements ProductReplyCustomRepository {

    public ProductReplyCustomRepositoryImpl() {
        super(ProductReply.class);
    }


}