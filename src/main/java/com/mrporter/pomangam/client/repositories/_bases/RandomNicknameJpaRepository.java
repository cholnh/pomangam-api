package com.mrporter.pomangam.client.repositories._bases;

import com.mrporter.pomangam.client.domains._bases.RandomNickname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RandomNicknameJpaRepository extends JpaRepository<RandomNickname, Integer> {
    @Query(value = "SELECT u.first FROM random_nickname_tbl u ORDER BY RAND() LIMIT 1", nativeQuery = true)
    String findFirstByRandom();

    @Query(value = "SELECT u.second FROM random_nickname_tbl u ORDER BY RAND() LIMIT 1", nativeQuery = true)
    String findSecondByRandom();
}
