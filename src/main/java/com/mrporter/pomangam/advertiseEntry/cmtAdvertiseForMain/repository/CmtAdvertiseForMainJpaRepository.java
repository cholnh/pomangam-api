package com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.repository;

import com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.domain.cmtAdvertiseForMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CmtAdvertiseForMainJpaRepository extends JpaRepository<cmtAdvertiseForMain, Integer> {
}
