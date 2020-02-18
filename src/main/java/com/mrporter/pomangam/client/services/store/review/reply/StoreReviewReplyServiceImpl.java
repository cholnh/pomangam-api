package com.mrporter.pomangam.client.services.store.review.reply;

import com.mrporter.pomangam.client.domains.store.review.StoreReview;
import com.mrporter.pomangam.client.domains.store.review.reply.StoreReviewReply;
import com.mrporter.pomangam.client.domains.store.review.reply.StoreReviewReplyDto;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.store.review.StoreReviewJpaRepository;
import com.mrporter.pomangam.client.repositories.store.review.reply.StoreReviewReplyJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreReviewReplyServiceImpl implements StoreReviewReplyService {

    StoreReviewReplyJpaRepository storeReviewReplyJpaRepository;
    UserJpaRepository userJpaRepository;
    StoreReviewJpaRepository storeReviewJpaRepository;

    @Override
    public List<StoreReviewReplyDto> findByIdxStoreReview(Long rIdx, Long uIdx, Pageable pageable) {
        List<StoreReviewReply> entities = storeReviewReplyJpaRepository.findByIdxStoreReview(rIdx, pageable).getContent();
        return fromEntitiesCustom(entities, uIdx);
    }

    @Override
    public StoreReviewReplyDto findByIdx(Long idx, Long uIdx) {
        StoreReviewReply entity = storeReviewReplyJpaRepository.findById(idx).get();
        return fromEntityCustom(entity, uIdx);
    }

    @Override
    public long count() {
        return storeReviewReplyJpaRepository.count();
    }

    @Override
    public StoreReviewReplyDto save(StoreReviewReplyDto dto) {
        // 댓글 추가
        StoreReviewReply entity = storeReviewReplyJpaRepository.save(dto.toEntity());

        // 총 댓글 수 증가
        addCntReply(dto.getIdxStoreReview());

        return StoreReviewReplyDto.fromEntity(entity);
    }

    @Override
    public StoreReviewReplyDto update(StoreReviewReplyDto dto) {
        Optional<StoreReviewReply> optionalStoreReviewReply = storeReviewReplyJpaRepository.findById(dto.getIdx());
        if(optionalStoreReviewReply.isPresent()) {
            // 댓글 수정
            StoreReviewReply entity = optionalStoreReviewReply.get();
            return StoreReviewReplyDto.fromEntity(storeReviewReplyJpaRepository.save(entity.update(dto.toEntity())));
        }
        return null;
    }

    @Override
    public void delete(Long rIdx, Long idx) {
        // 총 댓글 수 감소
        subCntReply(rIdx);

        // 댓글 삭제
        storeReviewReplyJpaRepository.deleteById(idx);
    }

    /**
     * 리뷰 댓글 수 증가
     */
    private void addCntReply(Long idxReview) {
        Optional<StoreReview> optionalStoreReview = storeReviewJpaRepository.findById(idxReview);
        if(optionalStoreReview.isPresent()) {
            StoreReview storeReview = optionalStoreReview.get();
            storeReview.addCntReply();
            storeReviewJpaRepository.save(storeReview);
        }
    }

    /**
     * 리뷰 댓글 수 감소
     */
    private void subCntReply(Long idxReview) {
        Optional<StoreReview> optionalStoreReview = storeReviewJpaRepository.findById(idxReview);
        if(optionalStoreReview.isPresent()) {
            StoreReview storeReview = optionalStoreReview.get();
            storeReview.subCntReply();
            storeReviewJpaRepository.save(storeReview);
        }
    }

    /**
     * entity -> dto 변환
     * 익명처리 핸들링
     *
     * @param entities 엔티티 리스트
     * @return dto 리스트
     */
    private List<StoreReviewReplyDto> fromEntitiesCustom(List<StoreReviewReply> entities, Long uIdx) {
        List<StoreReviewReplyDto> dtos = new ArrayList<>();
        for(StoreReviewReply entity : entities) {
            StoreReviewReplyDto dto = fromEntityCustom(entity, uIdx);
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
    private StoreReviewReplyDto fromEntityCustom(StoreReviewReply entity, Long uIdx) {
        StoreReviewReplyDto dto = StoreReviewReplyDto.fromEntity(entity);

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
