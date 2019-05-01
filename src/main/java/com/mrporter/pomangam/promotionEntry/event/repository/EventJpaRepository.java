package com.mrporter.pomangam.promotionEntry.event.repository;

import com.mrporter.pomangam.promotionEntry.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface EventJpaRepository extends JpaRepository<Event, Integer> {
}
