package com.mrporter.pomangam.client.repositories.notice;

import com.mrporter.pomangam.client.domains.notice.Notice;
import com.mrporter.pomangam.client.domains.notice.QNotice;
import com.mrporter.pomangam.client.domains.notice.QNoticeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(exported = false)
public interface NoticeJpaRepository extends JpaRepository<Notice, Long>, NoticeCustomRepository {
    Optional<Notice> findByIdxAndIsActiveIsTrue(Long idx);
}

interface NoticeCustomRepository {
    Page<Notice> findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx, Pageable pageable); // N+1 문제 해결
}

@Transactional(readOnly = true)
class NoticeCustomRepositoryImpl extends QuerydslRepositorySupport implements NoticeCustomRepository {

    public NoticeCustomRepositoryImpl() {
        super(Notice.class);
    }

    @Override
    public Page<Notice> findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx, Pageable pageable) {
        QNotice notice = QNotice.notice;
        QNoticeMapper noticeMapper = QNoticeMapper.noticeMapper;
        List<Notice> results =
                from(noticeMapper)
                .select(notice)
                .leftJoin(noticeMapper.notice, notice)
                .where(noticeMapper.deliverySite.idx.eq(dIdx))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }
}
