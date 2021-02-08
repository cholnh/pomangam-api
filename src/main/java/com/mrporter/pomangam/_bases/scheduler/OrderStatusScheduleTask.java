package com.mrporter.pomangam._bases.scheduler;

import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.ordertime.OrderTimeDto;
import com.mrporter.pomangam.client.services.order.OrderServiceImpl;
import com.mrporter.pomangam.client.services.order.sub_service.CommonSubService;
import com.mrporter.pomangam.client.services.ordertime.OrderTimeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderStatusScheduleTask {

    OrderServiceImpl orderService;
    ScheduleTaskService scheduleTaskService;
    OrderTimeServiceImpl orderTimeService;
    CommonSubService commonSubService;

    static boolean isStart = false;

    public void taskOrderSuccess() {
        List<OrderTimeDto> orderTimes = orderTimeService.findByIdxStore(1L);
        for(OrderTimeDto orderTime : orderTimes) {
            final LocalTime pickUpTime = orderTime.getPickUpTime();
            scheduleTaskService.addTaskToScheduler(1, "0 " + pickUpTime.getMinute() +  " " + pickUpTime.getHour() + " * * *", () -> {
                System.out.println("id: 1) 0 " + pickUpTime.getMinute() +  " " + pickUpTime.getHour() + " * * *");
                List<Order> orders = orderService.findToday(OrderType.DELIVERY_READY);
                for(Order order : orders) {
                    commonSubService.log(order.getIdx(), OrderType.DELIVERY_PICKUP);
                }
            });

            final LocalTime arrivalTime = orderTime.getArrivalTime().plusHours(1);
            scheduleTaskService.addTaskToScheduler(2, "0 " + arrivalTime.getMinute() +  " " + arrivalTime.getHour() + " * * *", () -> {
                System.out.println("id: 2) 0 " + arrivalTime.getMinute() +  " " + arrivalTime.getHour() + " * * *");
                List<Order> orders = orderService.findToday(OrderType.DELIVERY_PICKUP, OrderType.DELIVERY_DELAY);
                for(Order order : orders) {
                    orderService.deliverySuccess(order.getIdx());
                    // commonSubService.log(order.getIdx(), OrderType.DELIVERY_SUCCESS);
                }
            });
        }
    }

    @Scheduled(fixedRate = 10000000L)
    public void taskInitialize() {
        if(!isStart) {
            taskOrderSuccess();
            isStart = true;
        }
    }
}
