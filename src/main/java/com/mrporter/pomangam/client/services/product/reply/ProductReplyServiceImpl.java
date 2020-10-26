package com.mrporter.pomangam.client.services.product.reply;

import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.product.reply.ProductReply;
import com.mrporter.pomangam.client.domains.product.reply.ProductReplyDto;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.product.ProductJpaRepository;
import com.mrporter.pomangam.client.repositories.product.reply.ProductReplyJpaRepository;
import com.mrporter.pomangam.client.repositories.product.reply.like.ProductReplyLikeJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductReplyServiceImpl implements ProductReplyService {

    ProductReplyJpaRepository productReplyRepo;
    ProductReplyLikeJpaRepository productReplyLikeRepo;
    UserJpaRepository userRepo;
    ProductJpaRepository productRepo;

    @Override
    public List<ProductReplyDto> findByIdxProduct(Long pIdx, Long uIdx, Pageable pageable) {
        List<ProductReply> entities = productReplyRepo.findByIdxProductAndIsActiveIsTrue(pIdx, pageable).getContent();
        return fromEntitiesCustom(entities, uIdx);
    }

    @Override
    public ProductReplyDto findByIdx(Long idx, Long uIdx) {
        ProductReply entity = productReplyRepo.findByIdxAndIsActiveIsTrue(idx);
        return fromEntityCustom(entity, uIdx);
    }

    @Override
    public long count() {
        return productReplyRepo.countByIsActiveIsTrue();
    }

    @Override
    @Transactional
    public ProductReplyDto save(ProductReplyDto dto) {
        // 댓글 추가
        ProductReply entity = productReplyRepo.save(dto.toEntity());

        // 총 댓글 수 증가
        addCntReply(dto.getIdxProduct());

        return ProductReplyDto.fromEntity(entity);
    }

    @Override
    @Transactional
    public ProductReplyDto update(ProductReplyDto dto) {
        // 댓글 수정
        ProductReply entity = productReplyRepo.findByIdxAndIsActiveIsTrue(dto.getIdx());
        return ProductReplyDto.fromEntity(productReplyRepo.save(entity.update(dto.toEntity())));
    }

    @Override
    @Transactional
    public void delete(Long rIdx, Long idx) {
        // 총 댓글 수 감소
        subCntReply(rIdx);

        // 댓글 삭제
        productReplyRepo.deleteById(idx);
    }

    /**
     * 리뷰 댓글 수 증가
     */
    private void addCntReply(Long idxProduct) {
        Product product = productRepo.findByIdxAndIsActiveIsTrue(idxProduct);
        product.addCntReply();
        productRepo.save(product);
    }

    /**
     * 리뷰 댓글 수 감소
     */
    private void subCntReply(Long idxProduct) {
        Product product = productRepo.findByIdxAndIsActiveIsTrue(idxProduct);
        product.subCntReply();
        productRepo.save(product);
    }

    /**
     * entity -> dto 변환
     * 익명처리 핸들링
     *
     * @param entities 엔티티 리스트
     * @return dto 리스트
     */
    private List<ProductReplyDto> fromEntitiesCustom(List<ProductReply> entities, Long uIdx) {
        List<ProductReplyDto> dtos = new ArrayList<>();
        for(ProductReply entity : entities) {
            ProductReplyDto dto = fromEntityCustom(entity, uIdx);
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
    private ProductReplyDto fromEntityCustom(ProductReply entity, Long uIdx) {
        ProductReplyDto dto = ProductReplyDto.fromEntity(entity);
        User user = userRepo.findByIdxAndIsActiveIsTrue(entity.getIdxUser());
        if (uIdx != null && uIdx.compareTo(user.getIdx()) == 0) {  // isOwn 처리
            dto.setIsOwn(true);
            dto.setIsLike(productReplyLikeRepo.existsByIdxUserAndIdxProductReply(uIdx, entity.getIdx()));
            if (entity.getIsAnonymous()) { // anonymous 처리
                dto.setNickname("나 (익명)");
            } else {
                dto.setNickname(user.getNickname());
            }

        } else {
            dto.setIsOwn(false);
            dto.setIsLike(false);
            if (entity.getIsAnonymous()) { // anonymous 처리
                dto.setNickname("익명");
            } else {
                dto.setNickname(user.getNickname());
            }
        }
        return dto;
    }
}
