package com.mrporter.pomangam.feedbackHistory.likeForReplyAll.repository;

import com.mrporter.pomangam.feedbackHistory.likeForReplyAll.domain.LikeForReplyAll;
import com.mrporter.pomangam.feedbackHistory.likeForReplyAll.domain.LikeForReplyAllKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface LikeForReplyAllJpaRepository extends JpaRepository<LikeForReplyAll, LikeForReplyAllKey> {
}
