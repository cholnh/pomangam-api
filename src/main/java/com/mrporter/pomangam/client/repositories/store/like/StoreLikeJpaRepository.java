package com.mrporter.pomangam.client.repositories.store.like;

import com.mrporter.pomangam.client.domains.store.like.StoreLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;



@RepositoryRestResource(exported = false)
public interface StoreLikeJpaRepository extends JpaRepository<StoreLike, Long>, StoreLikeCustomRepository {

    boolean existsByIdxUser(Long idxUser);

    /**
     * [주의사항]
     * deleteByXXX 등의 메소드를 이용하는 삭제는 단건이 아닌 여러건을 삭제하더라도
     * 먼저 조회를 하고 그 결과로 얻은 엔티티 데이터를 1건씩 삭제함.
     * 만약, 1억건 중 50만건을 삭제한다고 하면 50만건을 먼저 조회 후 건건으로 삭제.
     * 이 메서드는 단건삭제 이므로 deleteByXXX 이용하여 처리하여도 무방함.
     * -> 여러건 삭제 쿼리의 경우: 직접 범위 조건의 삭제 쿼리를 생성해야 함.
     */
    @Modifying
    @Transactional
    void deleteByIdxUserAndIdxStore(Long idxUser, Long idxStore);

    /**
     * 범위 조건의 삭제 쿼리 (위 문제를 해결)
     */
    @Transactional
    @Modifying
    @Query(value = "delete from store_like_tbl where idx_user = :uIdx and idx_store = :sIdx", nativeQuery = true)
    void deleteByIdxUserAndIdxStoreQuery(@Param("uIdx") Long uIdx, @Param("sIdx") Long sIdx);
}

interface StoreLikeCustomRepository {
}

@Transactional(readOnly = true)
class StoreLikeCustomRepositoryImpl extends QuerydslRepositorySupport implements StoreLikeCustomRepository {

    public StoreLikeCustomRepositoryImpl() {
        super(StoreLike.class);
    }

}
