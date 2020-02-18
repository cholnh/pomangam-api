package com.mrporter.pomangam.client.services.store.review;

import com.mrporter.pomangam._bases.files.service.FileStorageServiceImpl;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.review.StoreReview;
import com.mrporter.pomangam.client.domains.store.review.StoreReviewDto;
import com.mrporter.pomangam.client.domains.store.review.image.StoreReviewImage;
import com.mrporter.pomangam.client.domains.store.review.image.StoreReviewImageType;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import com.mrporter.pomangam.client.repositories.store.review.StoreReviewJpaRepository;
import com.mrporter.pomangam.client.repositories.store.review.image.StoreReviewImageJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.client.services._bases.ImagePath;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreReviewServiceImpl implements StoreReviewService {

    StoreJpaRepository storeJpaRepository;
    StoreReviewJpaRepository storeReviewJpaRepository;
    UserJpaRepository userJpaRepository;
    FileStorageServiceImpl fileStorageService;
    StoreReviewImageJpaRepository storeReviewImageJpaRepository;

    @Override
    public List<StoreReviewDto> findByIdxStore(Long sIdx, Long uIdx, Pageable pageable) {
        List<StoreReview> entities = storeReviewJpaRepository.findByIdxStore(sIdx, pageable).getContent();
        return fromEntitiesCustom(entities, uIdx);
    }

    @Override
    public StoreReviewDto findByIdx(Long idx, Long uIdx) {
        StoreReview entity = storeReviewJpaRepository.findById(idx).get();
        return fromEntityCustom(entity, uIdx);
    }

    @Override
    public long count() {
        return storeReviewJpaRepository.count();
    }

    @Override
    public StoreReviewDto save(StoreReviewDto dto,  MultipartFile[] images) {
        // 리뷰 추가
        StoreReview entity = storeReviewJpaRepository.save(dto.toEntity());

        // 업체 평점 재 계산, 총 리뷰 수 증가
        addAvgStar(dto.getIdxStore(), entity.getIdx());

        // 리뷰 이미지 저장
        String imagePath = ImagePath.reviews(dto.getIdxDeliverySite(), dto.getIdxStore(), entity.getIdx());
        List<StoreReviewImage> savedImages = saveImage(imagePath, images);
        entity.addImages(savedImages);
        return StoreReviewDto.fromEntity(storeReviewJpaRepository.save(entity));
    }

    @Override
    public StoreReviewDto update(StoreReviewDto dto,  MultipartFile[] images) {
        Optional<StoreReview> optionalStoreReview = storeReviewJpaRepository.findById(dto.getIdx());
        if(optionalStoreReview.isPresent()) {
            // 리뷰 수정
            StoreReview entity = optionalStoreReview.get();
            entity = storeReviewJpaRepository.save(entity.update(dto.toEntity()));

            // 리뷰 이미지 수정
            boolean isImageUpdated = dto.getIsImageUpdated() != null && dto.getIsImageUpdated().booleanValue();
            if(isImageUpdated) {
                // 기존 이미지 파일 삭제
                String imagePath = ImagePath.reviews(dto.getIdxDeliverySite(), dto.getIdxStore(), dto.getIdx());
                fileStorageService.deleteFile(imagePath, true);
                entity.clearImages();

                if(images.length > 0) {
                    // 새로운 이미지 파일 저장
                    List<StoreReviewImage> savedImages = saveImage(imagePath, images);
                    entity.addImages(savedImages);
                }
            }
            return StoreReviewDto.fromEntity(storeReviewJpaRepository.save(entity));
        }
        return null;
    }

    @Override
    public void delete(Long dIdx, Long sIdx, Long idx) {
        // 업체 평점 재 계산, 총 리뷰 수 감소
        subAvgStar(sIdx, idx);

        // 리뷰 삭제
        storeReviewJpaRepository.deleteById(idx);

        // 리뷰 이미지 삭제
        String imagePath = ImagePath.reviews(dIdx, sIdx, idx);
        fileStorageService.deleteFile(imagePath, true);
    }

    private List<StoreReviewImage> saveImage(String imagePath, MultipartFile[] images) {
        List<StoreReviewImage> storeReviewImages = new ArrayList<>();
        for(int i=0; i<images.length; i++) {
            MultipartFile image = images[i];
            String fileName = (i+1) + "." + FilenameUtils.getExtension(image.getOriginalFilename());

            // 리뷰 이미지 파일 저장
            fileStorageService.storeFile(image, imagePath, fileName);

            // 리뷰 이미지 DB 저장
            storeReviewImages.add(StoreReviewImage.builder()
                    .imagePath(imagePath + fileName)
                    .imageType(i==0 ? StoreReviewImageType.MAIN : StoreReviewImageType.SUB)
                    .sequence(i+1)
                    .build());
        }
        return storeReviewImages;
    }

    /**
     * 업체 평점 재계산 (add)
     */
    private void addAvgStar(Long sIdx, Long rIdx) {
        Optional<Store> optionalStore = storeJpaRepository.findById(sIdx);
        if(optionalStore.isPresent()) {
            Store store = optionalStore.get();
            Optional<StoreReview> optionalStoreReview = storeReviewJpaRepository.findById(rIdx);
            if(optionalStoreReview.isPresent()) {
                StoreReview storeReview = optionalStoreReview.get();
                store.addCntComment(storeReview.getStar());
                storeJpaRepository.save(store);
            }
        }
    }

    /**
     * 업체 평점 재계산 (sub)
     */
    private void subAvgStar(Long sIdx, Long rIdx) {
        Optional<Store> optionalStore = storeJpaRepository.findById(sIdx);
        if(optionalStore.isPresent()) {
            Store store = optionalStore.get();
            Optional<StoreReview> optionalStoreReview = storeReviewJpaRepository.findById(rIdx);
            if(optionalStoreReview.isPresent()) {
                StoreReview storeReview = optionalStoreReview.get();
                store.subCntComment(storeReview.getStar());
                storeJpaRepository.save(store);
            }
        }
    }

    /**
     * entity -> dto 변환
     * 익명처리 핸들링
     *
     * @param entities 엔티티 리스트
     * @return dto 리스트
     */
    private List<StoreReviewDto> fromEntitiesCustom(List<StoreReview> entities, Long uIdx) {
        List<StoreReviewDto> dtos = new ArrayList<>();
        for(StoreReview entity : entities) {
            StoreReviewDto dto = fromEntityCustom(entity, uIdx);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }

    /**
     * entity -> dto 변환
     * 익명처리 핸들링
     * Todo. "익명" -> Globalization 상수 처리
     *
     * @param entity 변환할 엔티티
     * @return dto 반환. 만약 User 가 존재하지 않는다면 null 반환
     */
    private StoreReviewDto fromEntityCustom(StoreReview entity, Long uIdx) {
        StoreReviewDto dto = StoreReviewDto.fromEntity(entity);

        Optional<User> optional = userJpaRepository.findById(entity.getIdxUser());
        if(optional.isPresent()) {
            User user = optional.get();
            if (uIdx != null && uIdx.compareTo(user.getIdx()) == 0) {  // isOwn 처리
                dto.setIsOwn(true);
                if (entity.getIsAnonymous()) { // anonymous 처리
                    dto.setNickname(user.getNickname()+"(익명)");
                } else {
                    dto.setNickname(user.getNickname());
                }
            } else {
                dto.setIsOwn(false);
                if (entity.getIsAnonymous()) { // anonymous 처리
                    dto.setNickname("익명");
                } else {
                    dto.setNickname(user.getNickname());
                }
            }
            return dto;
        }
        return null;
    }
}
