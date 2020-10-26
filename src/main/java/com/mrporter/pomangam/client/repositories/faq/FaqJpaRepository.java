package com.mrporter.pomangam.client.repositories.faq;

import com.mrporter.pomangam.client.domains.faq.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface FaqJpaRepository extends JpaRepository<Faq, Long>, FaqCustomRepository {

}

interface FaqCustomRepository {
    Page<FaqCategory> findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx, Pageable pageable); // N+1 문제 해결
}

@Transactional(readOnly = true)
class FaqCustomRepositoryImpl extends QuerydslRepositorySupport implements FaqCustomRepository {

    public FaqCustomRepositoryImpl() {
        super(Faq.class);
    }

    @Override
    public Page<FaqCategory> findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx, Pageable pageable) {
        QFaqCategory faqCategory = QFaqCategory.faqCategory;
        QFaqCategoryMapper faqMapper = QFaqCategoryMapper.faqCategoryMapper;
        List<FaqCategory> results =
                from(faqMapper)
                .select(faqCategory)
                .leftJoin(faqMapper.faqCategory, faqCategory)
                .where(faqMapper.deliverySite.idx.eq(dIdx))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }
}
