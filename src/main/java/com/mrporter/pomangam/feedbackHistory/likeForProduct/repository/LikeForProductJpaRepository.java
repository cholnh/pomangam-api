package com.mrporter.pomangam.feedbackHistory.likeForProduct.repository;

import com.mrporter.pomangam.feedbackHistory.likeForProduct.domain.LikeForProduct;
import com.mrporter.pomangam.feedbackHistory.likeForProduct.domain.LikeForProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface LikeForProductJpaRepository extends JpaRepository<LikeForProduct, LikeForProductKey> {
}
