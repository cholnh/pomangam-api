package com.mrporter.pomangam.advertiseEntry.advertiseForMain.repository;

import com.mrporter.pomangam.advertiseEntry.advertiseForMain.domain.AdvertiseForMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface AdvertiseForMainJpaRepository extends JpaRepository<AdvertiseForMain, Integer> {
}
