package com.mrporter.pomangam.storeEntry.scheduleForStore.repository;

import com.mrporter.pomangam.storeEntry.scheduleForStore.domain.ScheduleForStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface ScheduleForStoreJpaRepository extends JpaRepository<ScheduleForStore, Integer> {
}

