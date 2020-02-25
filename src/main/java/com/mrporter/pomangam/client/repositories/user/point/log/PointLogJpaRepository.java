package com.mrporter.pomangam.client.repositories.user.point.log;

import com.mrporter.pomangam.client.domains.user.point.log.PointLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface PointLogJpaRepository extends JpaRepository<PointLog, Long>, PointLogCustomRepository {

}

interface PointLogCustomRepository {

}

@Transactional(readOnly = true)
class PointLogCustomRepositoryImpl extends QuerydslRepositorySupport implements PointLogCustomRepository {

    public PointLogCustomRepositoryImpl() {
        super(PointLog.class);
    }


}
