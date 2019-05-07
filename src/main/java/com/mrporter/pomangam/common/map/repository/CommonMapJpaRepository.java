package com.mrporter.pomangam.common.map.repository;

import com.mrporter.pomangam.common.map.domain.CommonMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = true)
public interface CommonMapJpaRepository extends JpaRepository<CommonMap, Integer> {

    List<CommonMap> findByKey(@Param("key") String key);

    @Query(value = "SELECT * FROM common_map_tbl WHERE idx = :idx", nativeQuery = true)
    CommonMap findByIdx(@Param("idx") Integer idx);
}
