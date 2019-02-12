package com.mrporter.pomangam.feedbackHistory.replyForCommentStore.repository;

import com.mrporter.pomangam.feedbackHistory.replyForCommentStore.domain.ReplyForCommentStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface ReplyForCommentStoreJpaRepository extends JpaRepository<ReplyForCommentStore, Integer> {
}
