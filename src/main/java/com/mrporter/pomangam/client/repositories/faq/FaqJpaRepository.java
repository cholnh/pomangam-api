package com.mrporter.pomangam.client.repositories.faq;

import com.mrporter.pomangam.client.domains.faq.Faq;
import com.mrporter.pomangam.client.domains.faq.QFaq;
import com.mrporter.pomangam.client.domains.faq.QFaqMapper;
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
    Page<Faq> findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx, Pageable pageable); // N+1 문제 해결
}

@Transactional(readOnly = true)
class FaqCustomRepositoryImpl extends QuerydslRepositorySupport implements FaqCustomRepository {

    public FaqCustomRepositoryImpl() {
        super(Faq.class);
    }

    @Override
    public Page<Faq> findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx, Pageable pageable) {
        QFaq faq = QFaq.faq;
        QFaqMapper faqMapper = QFaqMapper.faqMapper;
        List<Faq> results =
                from(faqMapper)
                .select(faq)
                .leftJoin(faqMapper.faq, faq)
                .where(faqMapper.deliverySite.idx.eq(dIdx))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }
}
