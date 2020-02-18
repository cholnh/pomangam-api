package com.mrporter.pomangam.client.repositories.map;

import com.mrporter.pomangam.client.domains.map.CommonMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface CommonMapJpaRepository extends JpaRepository<CommonMap, Long> {

    List<CommonMap> findByKey(@Param("key") String key);

    @Query(value = "SELECT * FROM common_map_tbl WHERE idx = :idx", nativeQuery = true)
    CommonMap findByIdx(@Param("idx") Long idx);
}
