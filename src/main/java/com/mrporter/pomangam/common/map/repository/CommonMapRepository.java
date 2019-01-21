package com.mrporter.pomangam.common.map.repository;

import com.mrporter.pomangam.common.map.domain.CommonMapTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = true)
public interface CommonMapRepository extends JpaRepository<CommonMapTbl, Long> {

    List<CommonMapTbl> findByKey(String key);

    @Modifying
    @Query(value = "SELECT * FROM common_map_tbl WHERE idx = :idx", nativeQuery = true)
    List<CommonMapTbl> findByCommonMapTblByIdx(@Param("idx") Long idx);

}
