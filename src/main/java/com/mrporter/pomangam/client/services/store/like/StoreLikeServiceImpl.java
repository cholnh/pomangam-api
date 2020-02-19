package com.mrporter.pomangam.client.services.store.like;

import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.like.StoreLike;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import com.mrporter.pomangam.client.repositories.store.like.StoreLikeJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreLikeServiceImpl implements StoreLikeService {

    StoreLikeJpaRepository storeLikeJpaRepository;
    StoreJpaRepository storeJpaRepository;
    UserJpaRepository userJpaRepository;

    @Override
    public boolean toggle(String phoneNumber, Long sIdx) {
        Long uIdx = userJpaRepository.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        boolean like = storeLikeJpaRepository.existsByIdxUser(uIdx);
        if(like) {
            cancelLike(uIdx, sIdx);
        } else {
            like(uIdx, sIdx);
        }
        return !like;
    }

    @Override
    public void like(String phoneNumber, Long sIdx) {
        Long uIdx = userJpaRepository.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        like(uIdx, sIdx);
    }

    @Override
    public void cancelLike(String phoneNumber, Long sIdx) {
        Long uIdx = userJpaRepository.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        cancelLike(uIdx, sIdx);
    }

    private void like(Long uIdx, Long sIdx) {
        saveLike(uIdx, sIdx);
        addCntLike(sIdx);
    }

    private void cancelLike(Long uIdx, Long sIdx) {
        deleteLike(uIdx, sIdx);
        subCntLike(sIdx);
    }

    private void saveLike(Long uIdx, Long sIdx) {
        StoreLike like = StoreLike.builder()
                .idxStore(sIdx)
                .idxUser(uIdx)
                .build();
        storeLikeJpaRepository.save(like);
    }

    private void deleteLike(Long uIdx, Long sIdx) {
        storeLikeJpaRepository.deleteByIdxUserAndIdxStoreQuery(uIdx, sIdx);
    }

    private void addCntLike(Long sIdx) {
        Store store = storeJpaRepository.findByIdxAndIsActiveIsTrue(sIdx);
        store.addCntLike();
        storeJpaRepository.save(store);
    }
    private void subCntLike(Long sIdx) {
        Store store = storeJpaRepository.findByIdxAndIsActiveIsTrue(sIdx);
        store.subCntLike();
        storeJpaRepository.save(store);
    }
}
