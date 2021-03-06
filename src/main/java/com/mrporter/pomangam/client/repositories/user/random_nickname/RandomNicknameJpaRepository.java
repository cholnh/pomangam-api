package com.mrporter.pomangam.client.repositories.user.random_nickname;

import com.mrporter.pomangam.client.domains.user.random_nickname.RandomNickname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RandomNicknameJpaRepository extends JpaRepository<RandomNickname, Long> {
    @Query(value = "SELECT u.first FROM random_nickname_tbl u WHERE u.is_active = 'Y' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    String findFirstByRandomAndIsActiveIsTrue();

    @Query(value = "SELECT u.second FROM random_nickname_tbl u WHERE u.is_active = 'Y' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    String findSecondByRandomAndIsActiveIsTrue();
}

