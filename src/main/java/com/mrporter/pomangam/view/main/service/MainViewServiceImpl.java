package com.mrporter.pomangam.view.main.service;

import com.mrporter.pomangam.advertiseEntry.advertiseForMain.repository.AdvertiseForMainRepositoryImpl;
import com.mrporter.pomangam.advertiseEntry.advertiseForPopup.repository.AdvertiseForPopupRepositoryImpl;
import com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.repository.CmtAdvertiseForMainRepositoryImpl;
import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.repository.SubAdvertiseForMainRepositoryImpl;
import com.mrporter.pomangam.deliveryEntry.deliverySite.repository.DeliverySiteJpaRepository;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository.DetailForDeliverySiteRepositoryImpl;
import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeRepositoryImpl;
import com.mrporter.pomangam.view.main.domain.MainFirstViewDto;
import com.mrporter.pomangam.view.main.domain.MainSecondViewDto;
import com.mrporter.pomangam.view.main.domain.MainViewDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
public class MainViewServiceImpl implements MainViewService {

    AdvertiseForPopupRepositoryImpl advertiseForPopupRepository;
    AdvertiseForMainRepositoryImpl advertiseForMainRepository;
    DeliverySiteJpaRepository deliverySiteJpaRepository;
    DetailForDeliverySiteRepositoryImpl detailForDeliverySiteRepository;
    OrderTimeRepositoryImpl orderTimeRepository;
    CmtAdvertiseForMainRepositoryImpl cmtAdvertiseForMainRepository;
    SubAdvertiseForMainRepositoryImpl subAdvertiseForMainRepository;

    @Override
    public MainViewDto getMainDto(Integer delivery_site_idx) {
        MainViewDto dto = new MainViewDto();
        dto.setAdvertiseForPopupDtoList(advertiseForPopupRepository.getAdvertisePopupsByDeliverySiteIdx(delivery_site_idx));
        dto.setAdvertiseForMainDtoList(advertiseForMainRepository.getAdvertiseMainsByDeliverySiteIdx(delivery_site_idx));
        dto.setDeliverySiteDto(deliverySiteJpaRepository.getByDeliverySiteIdx(delivery_site_idx));
        dto.setDetailSiteDtoList(detailForDeliverySiteRepository.getDetailSitesByDeliverySiteIdxOrderBySequence(delivery_site_idx));
        dto.setCmtAdvertiseForMainWithCommentAllDtoList(cmtAdvertiseForMainRepository.getCmtAdvertiseMainsByDeliverySiteIdx(delivery_site_idx));
        dto.setSubAdvertiseForMainDtoList(subAdvertiseForMainRepository.getSubAdvertiseMainsByDeliverySiteIdx(delivery_site_idx));

        List<OrderTimeDto> orderTimeDtoList = orderTimeRepository.getOrderTimesByDeliverySiteIdxAndArrivalTime(delivery_site_idx);
        Calendar c = Calendar.getInstance();
        if(orderTimeDtoList.isEmpty()) {
            orderTimeDtoList = orderTimeRepository.getOrderTimesByDeliverySiteIdx(delivery_site_idx);
            c.add(Calendar.DATE, 1); // tomorrow
            dto.setArrival_date(new Date(c.getTime().getTime()));
        } else {
            dto.setArrival_date(new Date(c.getTime().getTime()));
        }
        dto.setOrderTimeDtoList(orderTimeDtoList);
        return dto;
    }

    @Override
    public MainFirstViewDto getMainFirstDto(Integer delivery_site_idx) {
        MainFirstViewDto dto = new MainFirstViewDto();
        dto.setAdvertiseForPopupDtoList(advertiseForPopupRepository.getAdvertisePopupsByDeliverySiteIdx(delivery_site_idx));
        dto.setAdvertiseForMainDtoList(advertiseForMainRepository.getAdvertiseMainsByDeliverySiteIdx(delivery_site_idx));
        dto.setDeliverySiteDto(deliverySiteJpaRepository.getByDeliverySiteIdx(delivery_site_idx));
        dto.setDetailSiteDtoList(detailForDeliverySiteRepository.getDetailSitesByDeliverySiteIdxOrderBySequence(delivery_site_idx));

        List<OrderTimeDto> orderTimeDtoList = orderTimeRepository.getOrderTimesByDeliverySiteIdxAndArrivalTime(delivery_site_idx);
        Calendar c = Calendar.getInstance();
        if(orderTimeDtoList.isEmpty()) {
            orderTimeDtoList = orderTimeRepository.getOrderTimesByDeliverySiteIdx(delivery_site_idx);
            c.add(Calendar.DATE, 1); // tomorrow
            dto.setArrival_date(new Date(c.getTime().getTime()));
        } else {
            dto.setArrival_date(new Date(c.getTime().getTime()));
        }
        dto.setOrderTimeDtoList(orderTimeDtoList);
        return dto;
    }

    @Override
    public MainSecondViewDto getMainSecondDto(Integer delivery_site_idx) {
        MainSecondViewDto dto = new MainSecondViewDto();
        dto.setCmtAdvertiseForMainWithCommentAllDtoList(cmtAdvertiseForMainRepository.getCmtAdvertiseMainsByDeliverySiteIdx(delivery_site_idx));
        dto.setSubAdvertiseForMainDtoList(subAdvertiseForMainRepository.getSubAdvertiseMainsByDeliverySiteIdx(delivery_site_idx));
        return dto;
    }
}
