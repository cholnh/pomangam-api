package com.mrporter.pomangam.feedbackHistory.commentAll.repository;

import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CommentAllJpaRepository extends JpaRepository<CommentAll, Integer> {
}
