package com.mrporter.pomangam.test.data.randomNickname;

import com.mrporter.pomangam.client.domains.user.random_nickname.RandomNickname;
import com.mrporter.pomangam.client.repositories.user.random_nickname.RandomNicknameJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RandomNicknameData {

    @Autowired
    RandomNicknameJpaRepository randomNicknameJpaRepository;

    @Transactional
    public void of(String[] ff, String[] ss) {
        for(int i=0; i< ff.length; i++) {
            RandomNickname rnick = RandomNickname.builder()
                    .first(ff[i])
                    .second(ss[i])
                    .build();
            randomNicknameJpaRepository.save(rnick);
        }
    }
}