package com.mrporter.pomangam.view.main.service;

import com.mrporter.pomangam.advertiseEntry.advertiseForMain.repository.AdvertiseForMainRepositoryImpl;
import com.mrporter.pomangam.advertiseEntry.advertiseForPopup.repository.AdvertiseForPopupRepositoryImpl;
import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.repository.SubAdvertiseForMainRepositoryImpl;
import com.mrporter.pomangam.deliveryEntry.deliverySite.repository.DeliverySiteRepositoryImpl;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository.DetailForDeliverySiteRepositoryImpl;
import com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.repository.ImageForCommentAllMainRepositoryImpl;
import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeRepositoryImpl;
import com.mrporter.pomangam.view.main.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
public class MainViewServiceImpl implements MainViewService {

    AdvertiseForPopupRepositoryImpl advertiseForPopupRepository;
    AdvertiseForMainRepositoryImpl advertiseForMainRepository;
    DeliverySiteRepositoryImpl deliverySiteRepository;
    DetailForDeliverySiteRepositoryImpl detailForDeliverySiteRepository;
    OrderTimeRepositoryImpl orderTimeRepository;
    ImageForCommentAllMainRepositoryImpl cmtAdvertiseForMainRepository;
    SubAdvertiseForMainRepositoryImpl subAdvertiseForMainRepository;

    @Override
    public MainViewDto getMainDto(Integer delivery_site_idx) {
        return getMainDtoNoCache(getMainDtoCache(delivery_site_idx), delivery_site_idx);
    }

    //@Cacheable(value="getMainDtoCache", key="#delivery_site_idx")
    public MainViewDto getMainDtoCache(Integer delivery_site_idx) {
        MainViewDto dto = new MainViewDto();

        // [/advertises/popup?deliverySiteIdx={deliverySiteIdx}]
        dto.setAdvertiseForPopupDtoList(advertiseForPopupRepository.getAdvertisePopupsByDeliverySiteIdx(delivery_site_idx));

        // [/advertises/main?deliverySiteIdx={deliverySiteIdx}]
        dto.setAdvertiseForMainDtoList(advertiseForMainRepository.getAdvertiseMainsByDeliverySiteIdx(delivery_site_idx));

        // [/deliverySites/{deliverySiteIdx}]
        dto.setDeliverySiteDto(deliverySiteRepository.getByDeliverySiteIdx(delivery_site_idx));

        // [/deliverySites/{deliverySiteIdx}/detailForDeliverySites]
        dto.setDetailSiteDtoList(detailForDeliverySiteRepository.getByDeliverySiteIdxOrderBySequence(delivery_site_idx));

        // [/images/cmtAdvertiseForMain?deliverySiteIdx={deliverySiteIdx}]
        dto.setImageForCommentAllMainWithCommentAllDtos(cmtAdvertiseForMainRepository.getImageForCommentAllMainByDeliverySiteIdx(delivery_site_idx));

        // [/subAdvertises/main?deliverySiteIdx={deliverySiteIdx}]
        dto.setSubAdvertiseForMainDtoList(subAdvertiseForMainRepository.getSubAdvertiseMainsByDeliverySiteIdx(delivery_site_idx));
        return dto;
    }

    public MainViewDto getMainDtoNoCache(MainViewDto dto, Integer delivery_site_idx) {
        MainOrderTImeDto timeDto = getMainDtoNoCache(delivery_site_idx);
        dto.setHours(timeDto.getHours());
        dto.setOrderTimeDtoList(timeDto.getOrderTimeDtoList());
        dto.setArrival_date(timeDto.getArrival_date());
        return dto;
    }

    @Override
    public MainOrderTImeDto getMainDtoNoCache(Integer delivery_site_idx) {
        MainOrderTImeDto dto = new MainOrderTImeDto();
        List<OrderTimeDto> orderTimeDtoList = orderTimeRepository.getOrderTimesByDeliverySiteIdxAndArrivalTime(delivery_site_idx);
        Calendar c = Calendar.getInstance();
        if(orderTimeDtoList.isEmpty()) {
            orderTimeDtoList = orderTimeRepository.getOrderTimesByDeliverySiteIdx(delivery_site_idx);
            c.add(Calendar.DATE, 1); // tomorrow
            dto.setArrival_date(new Date(c.getTime().getTime()));
        } else {
            dto.setArrival_date(new Date(c.getTime().getTime()));
        }

        List<Hour> hours = new ArrayList<>();
        for(OrderTimeDto tdto : orderTimeDtoList) {
            Hour hour = new Hour();
            int h = tdto.getArrival_time().toLocalTime().getHour();
            hour.setHour(h);
            List<Integer> minutes = new ArrayList<>();
            for(OrderTimeDto d : orderTimeDtoList) {
                int h2 = d.getArrival_time().toLocalTime().getHour();
                int m2 = d.getArrival_time().toLocalTime().getMinute();
                if(h == h2) {
                    minutes.add(m2);
                }
            }
            hour.setMinutes(minutes);
            hours.add(hour);
        }
        dto.setHours(hours);
        dto.setOrderTimeDtoList(orderTimeDtoList);

        return dto;
    }

    @Override
    public MainFirstViewDto getMainFirstDto(Integer delivery_site_idx) {
        MainFirstViewDto dto = new MainFirstViewDto();
        dto.setAdvertiseForPopupDtoList(advertiseForPopupRepository.getAdvertisePopupsByDeliverySiteIdx(delivery_site_idx));
        dto.setAdvertiseForMainDtoList(advertiseForMainRepository.getAdvertiseMainsByDeliverySiteIdx(delivery_site_idx));
        dto.setDeliverySiteDto(deliverySiteRepository.getByDeliverySiteIdx(delivery_site_idx));
        dto.setDetailSiteDtoList(detailForDeliverySiteRepository.getByDeliverySiteIdxOrderBySequence(delivery_site_idx));

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
        dto.setImageForCommentAllMainWithCommentAllDtos(cmtAdvertiseForMainRepository.getImageForCommentAllMainByDeliverySiteIdx(delivery_site_idx));
        dto.setSubAdvertiseForMainDtoList(subAdvertiseForMainRepository.getSubAdvertiseMainsByDeliverySiteIdx(delivery_site_idx));
        return dto;
    }
}
