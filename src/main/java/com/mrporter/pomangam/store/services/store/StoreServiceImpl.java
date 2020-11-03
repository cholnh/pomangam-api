package com.mrporter.pomangam.store.services.store;

import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.domains.store.info.ProductionInfo;
import com.mrporter.pomangam.client.domains.store.info.StoreInfo;
import com.mrporter.pomangam.client.domains.store.schedule.StoreSchedule;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    StoreJpaRepository storeRepo;

    @Override
    public StoreDto findByIdx(Long idx) {
        Store entity = storeRepo.findByIdxAndIsActiveIsTrue(idx);
        return StoreDto.fromEntity(entity);
    }

    @Override
    public StoreDto patch(StoreDto request) {
        final Store fetched = storeRepo
                .findById(request.getIdx())
                .orElseThrow(() -> new RuntimeException("invalid store patch dto"));
        if(fetched == null) {
            return null;
        }

        try {
            StoreInfo storeInfo = request.getStoreInfo();
            if(storeInfo != null) {
                if(storeInfo.getName() != null) {
                    fetched.getStoreInfo().setName(storeInfo.getName());
                }
                if(storeInfo.getDescription() != null) {
                    fetched.getStoreInfo().setDescription(storeInfo.getDescription());
                }
                if(storeInfo.getSubDescription() != null) {
                    fetched.getStoreInfo().setSubDescription(storeInfo.getSubDescription());
                }
                if(storeInfo.getOwnerName() != null) {
                    fetched.getStoreInfo().setOwnerName(storeInfo.getOwnerName());
                }
                if(storeInfo.getCompanyName() != null) {
                    fetched.getStoreInfo().setCompanyName(storeInfo.getCompanyName());
                }
                if(storeInfo.getCompanyLocation() != null) {
                    fetched.getStoreInfo().setCompanyLocation(storeInfo.getCompanyLocation());
                }
                if(storeInfo.getCompanyPhoneNumber() != null) {
                    fetched.getStoreInfo().setCompanyPhoneNumber(storeInfo.getCompanyPhoneNumber());
                }
            }

            ProductionInfo productionInfo = request.getProductionInfo();
            if(productionInfo != null) {
                if(productionInfo.getMinimumTime() != null) {
                    fetched.getProductionInfo().setMinimumTime(productionInfo.getMinimumTime());
                }
                if(productionInfo.getParallelProduction() != null) {
                    fetched.getProductionInfo().setParallelProduction(productionInfo.getParallelProduction());
                }
                if(productionInfo.getMaximumProduction() != null) {
                    fetched.getProductionInfo().setMaximumProduction(productionInfo.getMaximumProduction());
                }
            }

            StoreSchedule storeSchedule = request.getStoreSchedule();
            if(storeSchedule != null) {
                if(storeSchedule.getOpenTime() != null) {
                    fetched.getStoreSchedule().setOpenTime(storeSchedule.getOpenTime());
                }
                if(storeSchedule.getCloseTime() != null) {
                    fetched.getStoreSchedule().setCloseTime(storeSchedule.getCloseTime());
                }
                if(storeSchedule.getIsOpening() != null) {
                    fetched.getStoreSchedule().setIsOpening(storeSchedule.getIsOpening());
                }
                if(storeSchedule.getPauseDescription() != null) {
                    fetched.getStoreSchedule().setPauseDescription(storeSchedule.getPauseDescription());
                }
            }

            return StoreDto.fromEntity(storeRepo.save(fetched));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("invalid store patch dto", e);
        }
    }
}
