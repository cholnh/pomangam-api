package com.mrporter.pomangam.promotionEntry.notice.repository;

import com.mrporter.pomangam.promotionEntry.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface NoticeJpaRepository extends JpaRepository<Notice, Integer> {
}
