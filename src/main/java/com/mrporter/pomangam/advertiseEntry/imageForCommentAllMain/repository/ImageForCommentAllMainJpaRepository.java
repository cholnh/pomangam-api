package com.mrporter.pomangam.advertiseEntry.imageForCommentAllMain.repository;

import com.mrporter.pomangam.advertiseEntry.imageForCommentAllMain.domain.ImageForCommentAllMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface ImageForCommentAllMainJpaRepository extends JpaRepository<ImageForCommentAllMain, Integer> {
}
