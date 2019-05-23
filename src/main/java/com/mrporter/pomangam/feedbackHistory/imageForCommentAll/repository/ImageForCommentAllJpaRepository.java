package com.mrporter.pomangam.feedbackHistory.imageForCommentAll.repository;

import com.mrporter.pomangam.feedbackHistory.imageForCommentAll.domain.ImageForCommentAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = true)
public interface ImageForCommentAllJpaRepository extends JpaRepository<ImageForCommentAll, Integer> {
    List<ImageForCommentAll> findByCommentAllIdx(@Param("commentAllIdx") Integer commentAllIdx);
}

