package com.mrporter.pomangam.orderEntry.orderTime.controller;

import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTime;
import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;
import com.mrporter.pomangam.orderEntry.orderTime.service.OrderTimeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/orderTimes")
@RestController
@AllArgsConstructor
public class OrderTimeController {

    OrderTimeServiceImpl orderTimeService;

    @GetMapping("/search/getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime")
    public ResponseEntity<?> getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(@RequestParam("storeIdx") Integer store_idx,
                                                                              @RequestParam("deliverySiteIdx") Integer delivery_site_idx,
                                                                              @RequestParam("arrivalTime") String arrival_time) {
        OrderTime result = orderTimeService.getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(store_idx, delivery_site_idx, arrival_time);
        if(result == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

    @GetMapping("/search/getOrderTimesByDeliverySiteIdx")
    public List<OrderTimeDto> getOrderTimesByDeliverySiteIdx(@RequestParam("deliverySiteIdx") Integer delivery_site_idx) {
        return orderTimeService.getOrderTimesByDeliverySiteIdx(delivery_site_idx);
    }

    @GetMapping("/search/getOrderTimesNowByDeliverySiteIdx")
    public List<OrderTimeDto> getOrderTimesByDeliverySiteIdxAndArrivalTime(@RequestParam("deliverySiteIdx") Integer delivery_site_idx) {
        return orderTimeService.getOrderTimesByDeliverySiteIdxAndArrivalTime(delivery_site_idx);
    }
}
