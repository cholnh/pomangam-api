package com.mrporter.pomangam.feedbackHistory.likeForStore.repository;

import com.mrporter.pomangam.feedbackHistory.likeForStore.domain.LikeForStore;
import com.mrporter.pomangam.feedbackHistory.likeForStore.domain.LikeForStoreKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface LikeForStoreJpaRepository extends JpaRepository<LikeForStore, LikeForStoreKey> {
}
