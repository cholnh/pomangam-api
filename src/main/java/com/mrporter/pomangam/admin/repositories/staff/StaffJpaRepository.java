package com.mrporter.pomangam.admin.repositories.staff;

import com.mrporter.pomangam.admin.domains.staff.QStaff;
import com.mrporter.pomangam.admin.domains.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface StaffJpaRepository extends JpaRepository<Staff, Long>, StaffCustomRepository {
    Staff findByIdxAndIsActiveIsTrue(Long idx);
    Staff findByIdAndIsActiveIsTrue(String id);
    Boolean existsById(String id);
    void deleteById(String id);
}

interface StaffCustomRepository {
    Long findIdxByIdAndIsActiveIsTrue(String id);
}

@Transactional(readOnly = true)
class StaffCustomRepositoryImpl extends QuerydslRepositorySupport implements StaffCustomRepository {

    public StaffCustomRepositoryImpl() {
        super(Staff.class);
    }

    @Override
    public Long findIdxByIdAndIsActiveIsTrue(String id) {
        final QStaff staff = QStaff.staff;
        List<Long> idxes = from(staff)
                .select(staff.idx)
                .where(staff.id.eq(id)
                .and(staff.isActive.isTrue()))
                .fetch();
        if(idxes != null && !idxes.isEmpty()) {
            return idxes.get(0);
        }
        return null;
    }
}