package com.mrporter.pomangam.feedbackHistory.commentStore.repository;

import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CommentStoreJpaRepository extends JpaRepository<CommentStore, Integer> {
}
