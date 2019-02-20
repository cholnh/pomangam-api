package com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.repository;

import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.domain.SubAdvertiseForMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface SubAdvertiseForMainJpaRepository extends JpaRepository<SubAdvertiseForMain, Integer> {
}
