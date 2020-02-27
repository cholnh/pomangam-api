package com.mrporter.pomangam.client.repositories.event;

import com.mrporter.pomangam.client.domains.event.Event;
import com.mrporter.pomangam.client.domains.event.QEvent;
import com.mrporter.pomangam.client.domains.event.QEventMapper;
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
public interface EventJpaRepository extends JpaRepository<Event, Long>, EventCustomRepository {
    Optional<Event> findByIdxAndIsActiveIsTrue(Long idx);
}

interface EventCustomRepository {
    Page<Event> findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx, Pageable pageable); // N+1 문제 해결
}

@Transactional(readOnly = true)
class EventCustomRepositoryImpl extends QuerydslRepositorySupport implements EventCustomRepository {

    public EventCustomRepositoryImpl() {
        super(Event.class);
    }

    @Override
    public Page<Event> findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx, Pageable pageable) {
        QEvent event = QEvent.event;
        QEventMapper eventMapper = QEventMapper.eventMapper;
        List<Event> results =
                from(eventMapper)
                .select(event)
                .leftJoin(eventMapper.event, event)
                .where(eventMapper.deliverySite.idx.eq(dIdx))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }
}
