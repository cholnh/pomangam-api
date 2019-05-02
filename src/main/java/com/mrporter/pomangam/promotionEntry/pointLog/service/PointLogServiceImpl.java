package com.mrporter.pomangam.promotionEntry.pointLog.service;

import com.mrporter.pomangam.common.map.service.CommonMapServiceImpl;
import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.pointLog.domain.PointLog;
import com.mrporter.pomangam.promotionEntry.pointLog.domain.PointLogDto;
import com.mrporter.pomangam.promotionEntry.pointLog.domain.PointPctPrcDto;
import com.mrporter.pomangam.promotionEntry.pointLog.domain.StatePointLog;
import com.mrporter.pomangam.promotionEntry.pointLog.repository.PointLogRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PointLogServiceImpl implements PointLogService {

    PointLogRepositoryImpl pointLogRepository;
    CommonMapServiceImpl commonMapService;

    public List<PointLogDto> findByCustomerIdx(Integer customerIdx, PageRequest pageRequest) {
        return pointLogRepository.findByCustomerIdx(customerIdx, pageRequest);
    }

    @Override
    public void logUsed(Integer customerIdx, Integer orderIdx, Integer usedPoint, StatePointLog state) {
        PointLogDto dto = pointLogRepository.getLastNode(customerIdx);
        int postPrc = 0;
        int sequence = 1;
        if(dto != null) {
            postPrc = dto.getPost_prc().intValue();
            sequence = dto.getSequence().intValue() + 1;
        }

        PointLog log = new PointLog();
        log.setCustomer_idx(customerIdx);
        log.setOrder_idx(orderIdx);
        log.setPre_prc(postPrc);
        log.setPost_prc(postPrc-usedPoint.intValue());
        log.setUsing_point(usedPoint);
        log.setRegister_date(CustomTime.curTimestampSql());
        log.setType(state.getCode());
        log.setSequence(sequence);
    }

    @Override
    public void logSaved(Integer customerIdx, Integer orderIdx, Integer usedPoint, StatePointLog state) {
        PointLogDto dto = pointLogRepository.getLastNode(customerIdx);
        int postPrc = 0;
        int sequence = 1;
        if(dto != null) {
            postPrc = dto.getPost_prc().intValue();
            sequence = dto.getSequence().intValue() + 1;
        }

        PointLog log = new PointLog();
        log.setCustomer_idx(customerIdx);
        log.setOrder_idx(orderIdx);
        log.setPre_prc(postPrc);
        log.setPost_prc(postPrc+usedPoint.intValue());
        log.setUsing_point(usedPoint);
        log.setRegister_date(CustomTime.curTimestampSql());
        log.setType(state.getCode());
        log.setSequence(sequence);
    }

    @Override
    public PointPctPrcDto getPointPctPrc(Integer customerIdx) {
        if(customerIdx == null) {
            // return default value
        } else {
            // return custom value
        }
        int pct = Integer.parseInt(commonMapService.getValue("point-saving-pct-1").get(0).getValue());
        int prc = Integer.parseInt(commonMapService.getValue("point-saving-prc-1").get(0).getValue());

        return new PointPctPrcDto(pct, prc);
    }
}
