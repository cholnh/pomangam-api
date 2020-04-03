package com.mrporter.pomangam._bases.securities.kakaoauth.repository;

import com.mrporter.pomangam._bases.securities.kakaoauth.domain.KakaoAuthDto;
import com.mrporter.pomangam._bases.utils.queryrunner.QueryRunnerImpl;
import com.mrporter.pomangam._bases.utils.security.Ip;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@AllArgsConstructor
public class KakaoAuthRepositoryImpl implements KakaoAuthRepository {
    @PersistenceContext
    EntityManager em;

    final int CHECK_ABUSING_LIMIT_COUNT = 5;
    final int CHECK_ABUSING_LIMIT_MINUTE = 5;
    final int INPUT_AUTHCODE_LIMIT_MINUTE = 3;
    QueryRunnerImpl queryRunner;


    public boolean checkAbusing(String ip) {
        Query query = em
                .createNativeQuery("SELECT idx, phone_number, auth_code, ip, register_date FROM kakao_authcode_tbl where ip = ? AND TIMESTAMPDIFF(minute, register_date, now()) < ?")
                .setParameter(1, ip)
                .setParameter(2, CHECK_ABUSING_LIMIT_MINUTE);


        List<KakaoAuthDto> dto = new JpaResultMapper().list(query, KakaoAuthDto.class);
        if(dto == null || dto.size() <= CHECK_ABUSING_LIMIT_COUNT) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    @Override
    public void saveAuthCode(String phone_number, String auth_code) {
        em
                .createNativeQuery("INSERT INTO kakao_authcode_tbl (`phone_number`, `auth_code`, `register_date`, `ip`) VALUES (?, ?, now(), ?)")
                .setParameter(1, phone_number)
                .setParameter(2, auth_code)
                .setParameter(3, Ip.getInfo())
                .executeUpdate();
    }

    @Transactional
    @Override
    public boolean checkAuthCode(String phone_number, String auth_code) {
        Query query = em
                .createNativeQuery("SELECT idx, phone_number, auth_code, ip, register_date FROM kakao_authcode_tbl where phone_number = ? AND TIMESTAMPDIFF(minute, register_date, now()) < ? ORDER BY register_date DESC, idx desc")
                .setParameter(1, phone_number)
                .setParameter(2, INPUT_AUTHCODE_LIMIT_MINUTE);

        List<KakaoAuthDto> dto = new JpaResultMapper().list(query, KakaoAuthDto.class);
        if(dto == null || dto.isEmpty()) {
            return false;
        } else {
            if(auth_code.equals(dto.get(0).getAuth_code())) {
                em
                        .createNativeQuery("DELETE FROM kakao_authcode_tbl WHERE phone_number = ?")
                        .setParameter(1, phone_number)
                        .executeUpdate();
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean checkAuthCodeNotDelete(String phone_number, String auth_code) {
        Query query = em
                .createNativeQuery("SELECT idx, phone_number, auth_code, ip, register_date FROM kakao_authcode_tbl where phone_number = ? AND TIMESTAMPDIFF(minute, register_date, now()) < ? ORDER BY register_date DESC, idx desc")
                .setParameter(1, phone_number)
                .setParameter(2, INPUT_AUTHCODE_LIMIT_MINUTE);

        List<KakaoAuthDto> dto = new JpaResultMapper().list(query, KakaoAuthDto.class);
        if(dto == null || dto.isEmpty()) {
            return false;
        } else {
            if(auth_code.equals(dto.get(0).getAuth_code())) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Transactional
    public void deleteAuthCode(String phone_number) {
        em
            .createNativeQuery("DELETE FROM kakao_authcode_tbl WHERE phone_number = ?")
            .setParameter(1, phone_number)
            .executeUpdate();
    }
}
