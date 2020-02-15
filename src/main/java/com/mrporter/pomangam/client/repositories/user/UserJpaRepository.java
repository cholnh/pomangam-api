package com.mrporter.pomangam.client.repositories.user;

import com.mrporter.pomangam.client.domains.user.QUser;
import com.mrporter.pomangam.client.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface UserJpaRepository extends JpaRepository<User, Long>, UserCustomRepository {
    User findByPhoneNumber(String phoneNumber);
    Boolean existsByNickname(String nickname);
    void deleteByPhoneNumber(String phoneNumber);
}

interface UserCustomRepository {
    List<User> findRecentlyRegistered(int limit);   // for example
}

@Transactional(readOnly = true)
class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {

    public UserCustomRepositoryImpl() {
        super(User.class);
    }

    @Override
    // 최근 가입한 limit 갯수 만큼 유저 리스트를 가져온다
    public List<User> findRecentlyRegistered(int limit) {
        try {
            final QUser user = QUser.user;
            return from(user)
                    .limit(limit)
                    .orderBy(user.idx.desc())
                    .fetch();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}