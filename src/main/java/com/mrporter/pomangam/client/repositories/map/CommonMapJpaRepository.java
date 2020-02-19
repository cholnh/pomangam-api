package com.mrporter.pomangam.client.repositories.map;

import com.mrporter.pomangam.client.domains.map.CommonMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface CommonMapJpaRepository extends JpaRepository<CommonMap, Long> {

    List<CommonMap> findByKeyAndIsActiveIsTrue(@Param("key") String key);

    @Query(value = "SELECT * FROM common_map_tbl WHERE idx = :idx AND is_active = 'Y'", nativeQuery = true)
    CommonMap findByIdxAndIsActiveIsTrue(@Param("idx") Long idx);
}
