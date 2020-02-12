package com.mrporter.pomangam.admin.repositories.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface _FcmJpaRepository extends JpaRepository<FcmToken, String> {
}
