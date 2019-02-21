package com.mrporter.pomangam.advertiseEntry.advertiseForPopup.repository;

import com.mrporter.pomangam.advertiseEntry.advertiseForPopup.domain.AdvertiseForPopup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface AdvertiseForPopupJpaRepository extends JpaRepository<AdvertiseForPopup, Integer> {
}
