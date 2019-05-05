package com.mrporter.pomangam.feedbackHistory.likeForCommentStore.repository;

import com.mrporter.pomangam.feedbackHistory.likeForCommentStore.domain.LikeForCommentStore;
import com.mrporter.pomangam.feedbackHistory.likeForCommentStore.domain.LikeForCommentStoreKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface LikeForCommentStoreJpaRepository extends JpaRepository<LikeForCommentStore, LikeForCommentStoreKey> {
}
