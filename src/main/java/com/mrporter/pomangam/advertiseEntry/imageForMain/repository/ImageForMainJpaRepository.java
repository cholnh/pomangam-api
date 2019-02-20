package com.mrporter.pomangam.advertiseEntry.imageForMain.repository;

import com.mrporter.pomangam.advertiseEntry.imageForMain.domain.ImageForMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface ImageForMainJpaRepository extends JpaRepository<ImageForMain, Integer> {
}
