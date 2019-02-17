package com.mrporter.pomangam.feedbackHistory.likeForReplyStore.repository;

import com.mrporter.pomangam.feedbackHistory.likeForReplyStore.domain.LikeForReplyStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface LikeForReplyStoreJpaRepository extends JpaRepository<LikeForReplyStore, Integer> {
}
