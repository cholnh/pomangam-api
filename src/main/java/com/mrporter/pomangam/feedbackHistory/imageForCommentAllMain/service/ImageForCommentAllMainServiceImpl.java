package com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.service;

import com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.domain.ImageForCommentAllMainWithCommentAllDto;
import com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.repository.ImageForCommentAllMainRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageForCommentAllMainServiceImpl implements ImageForCommentAllMainService {

    ImageForCommentAllMainRepositoryImpl imageForCommentAllMainRepository;

    @Override
    public List<ImageForCommentAllMainWithCommentAllDto> getImageForCommentAllMainByDeliverySiteIdx(Integer delivery_site_idx) {
        return imageForCommentAllMainRepository.getImageForCommentAllMainByDeliverySiteIdx(delivery_site_idx);
    }
}
