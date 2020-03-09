package com.mrporter.pomangam.client.services.store.like;

import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.like.StoreLike;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import com.mrporter.pomangam.client.repositories.store.like.StoreLikeJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StoreLikeServiceImpl implements StoreLikeService {

    StoreLikeJpaRepository storeLikeRepo;
    StoreJpaRepository storeRepo;
    UserJpaRepository userRepo;

    @Override
    @Transactional
    public boolean toggle(String phoneNumber, Long sIdx) {
        Long uIdx = userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        boolean like = storeLikeRepo.existsByIdxUserAndIdxStore(uIdx, sIdx);
        if(like) {
            cancelLike(uIdx, sIdx);
        } else {
            like(uIdx, sIdx);
        }
        return !like;
    }

    @Override
    @Transactional
    public void like(String phoneNumber, Long sIdx) {
        Long uIdx = userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        like(uIdx, sIdx);
    }

    @Override
    @Transactional
    public void cancelLike(String phoneNumber, Long sIdx) {
        Long uIdx = userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
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
        storeLikeRepo.save(like);
    }

    private void deleteLike(Long uIdx, Long sIdx) {
        storeLikeRepo.deleteByIdxUserAndIdxStoreQuery(uIdx, sIdx);
    }

    private void addCntLike(Long sIdx) {
        Store store = storeRepo.findByIdxAndIsActiveIsTrue(sIdx);
        store.addCntLike();
        storeRepo.save(store);
    }
    private void subCntLike(Long sIdx) {
        Store store = storeRepo.findByIdxAndIsActiveIsTrue(sIdx);
        store.subCntLike();
        storeRepo.save(store);
    }
}
