package com.mrporter.pomangam.client.repositories.user;

import com.mrporter.pomangam.client.domains.user.QUser;
import com.mrporter.pomangam.client.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface UserJpaRepository extends JpaRepository<User, Long>, UserCustomRepository {
    User findByIdxAndIsActiveIsTrue(Long idx);
    User findByPhoneNumberAndIsActiveIsTrue(String phoneNumber);
    Boolean existsByNickname(String nickname);
    Boolean existsByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);
}

interface UserCustomRepository {
    Long findIdxByPhoneNumberAndIsActiveIsTrue(String phoneNumber);
    Long findIdxDeliverySiteByIdxAndIsActiveIsTrue(Long idx);
}

@Transactional(readOnly = true)
class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {

    public UserCustomRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Long findIdxByPhoneNumberAndIsActiveIsTrue(String phoneNumber) {
        final QUser user = QUser.user;
        List<Long> idxes = from(user)
                .select(user.idx)
                .where(user.phoneNumber.eq(phoneNumber)
                .and(user.isActive.isTrue()))
                .fetch();
        if(idxes != null && !idxes.isEmpty()) {
            return idxes.get(0);
        }
        return null;
    }

    @Override
    public Long findIdxDeliverySiteByIdxAndIsActiveIsTrue(Long idx) {
        final QUser user = QUser.user;
        List<Long> idxes = from(user)
                .select(user.deliveryDetailSite.idx)
                .where(user.idx.eq(idx)
                .and(user.isActive.isTrue()))
                .fetch();
        if(idxes != null && !idxes.isEmpty()) {
            return idxes.get(0);
        }
        return null;
    }
}