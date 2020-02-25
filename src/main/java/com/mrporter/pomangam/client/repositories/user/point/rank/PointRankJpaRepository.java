package com.mrporter.pomangam.client.repositories.user.point.rank;

import com.mrporter.pomangam.client.domains.user.point.rank.PointRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface PointRankJpaRepository extends JpaRepository<PointRank, Long>, PointRankCustomRepository {

}

interface PointRankCustomRepository {

}

@Transactional(readOnly = true)
class PointRankCustomRepositoryImpl extends QuerydslRepositorySupport implements PointRankCustomRepository {

    public PointRankCustomRepositoryImpl() {
        super(PointRank.class);
    }


}
