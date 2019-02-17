package com.mrporter.pomangam.feedbackHistory.likeForCommentAll.repository;

import com.mrporter.pomangam.feedbackHistory.likeForCommentAll.domain.LikeForCommentAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface LikeForCommentAllJpaRepository extends JpaRepository<LikeForCommentAll, Integer> {
}
