package com.mrporter.pomangam.orderEntry.orderLog.domain;

import com.mrporter.pomangam.orderEntry.order.domain.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Table(name = "log_for_order_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class OrderLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private Integer box_no;

    private Integer customer_idx;

    private Integer guest_idx;

    private Integer employee_idx;

    private Integer delivery_site_idx;

    private Integer detail_site_idx;

    private Byte type_payment;

    private Byte state_order;

    private Timestamp register_date;

    private Date arrival_date_only;

    private Time arrival_time_only;

    private Integer using_point;

    private Integer using_coupon_idx;

    private Integer final_amount;

    private String merchant_uid;

    private String imp_uid;

    private Integer saved_point;

    @Builder
    public OrderLog(Integer box_no, Integer customer_idx, Integer guest_idx, Integer employee_idx, Integer delivery_site_idx, Integer detail_site_idx, Byte type_payment, Byte state_order, Timestamp register_date, Date arrival_date_only, Time arrival_time_only, Integer using_point, Integer using_coupon_idx, Integer final_amount, String merchant_uid, String imp_uid, Integer saved_point) {
        this.box_no = box_no;
        this.customer_idx = customer_idx;
        this.guest_idx = guest_idx;
        this.employee_idx = employee_idx;
        this.delivery_site_idx = delivery_site_idx;
        this.detail_site_idx = detail_site_idx;
        this.type_payment = type_payment;
        this.state_order = state_order;
        this.register_date = register_date;
        this.arrival_date_only = arrival_date_only;
        this.arrival_time_only = arrival_time_only;
        this.using_point = using_point;
        this.using_coupon_idx = using_coupon_idx;
        this.final_amount = final_amount;
        this.merchant_uid = merchant_uid;
        this.imp_uid = imp_uid;
        this.saved_point = saved_point;
    }

    public OrderLog(Order order) {
        this.box_no = order.getBox_no();
        this.customer_idx = order.getCustomer_idx();
        this.guest_idx = order.getGuest_idx();
        this.employee_idx = order.getEmployee_idx();
        this.delivery_site_idx = order.getDelivery_site_idx();
        this.detail_site_idx = order.getDetail_site_idx();
        this.type_payment = order.getType_payment();
        this.state_order = order.getState_order();
        this.register_date = order.getRegister_date();
        this.arrival_date_only = order.getArrival_date_only();
        this.arrival_time_only = order.getArrival_time_only();
        this.using_point = order.getUsing_point();
        this.using_coupon_idx = order.getUsing_coupon_idx();
        this.final_amount = order.getFinal_amount();
        this.merchant_uid = order.getMerchantUid();
        this.imp_uid = order.getImp_uid();
        this.saved_point = order.getSaved_point();
    }
}
