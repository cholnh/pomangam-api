package com.mrporter.pomangam.store.repository.owner;

import com.mrporter.pomangam.store.domains.owner.Owner;
import com.mrporter.pomangam.store.domains.owner.QOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface OwnerJpaRepository extends JpaRepository<Owner, Long>, OwnerCustomRepository {
    Owner findByIdxAndIsActiveIsTrue(Long idx);
    Owner findByIdAndIsActiveIsTrue(String id);
    Boolean existsById(String id);
    void deleteById(String id);
}

interface OwnerCustomRepository {
    Long findIdxByIdAndIsActiveIsTrue(String id);
}

@Transactional(readOnly = true)
class OwnerCustomRepositoryImpl extends QuerydslRepositorySupport implements OwnerCustomRepository {

    public OwnerCustomRepositoryImpl() {
        super(Owner.class);
    }

    @Override
    public Long findIdxByIdAndIsActiveIsTrue(String id) {
        final QOwner owner = QOwner.owner;
        List<Long> idxes = from(owner)
                .select(owner.idx)
                .where(owner.id.eq(id)
                .and(owner.isActive.isTrue()))
                .fetch();
        if(idxes != null && !idxes.isEmpty()) {
            return idxes.get(0);
        }
        return null;
    }
}