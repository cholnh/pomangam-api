package com.mrporter.pomangam.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "order_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long box_no;

    private Long customer_idx;;

    private Long employee_idx;

    private Long delivery_site_idx;

    private Integer mode;

    private Integer status;

    private Integer total;

    private Date register_date;

    private Date receive_date;

    @Builder
    public Order(Long box_no, Long customer_idx, Long employee_idx, Long delivery_site_idx, Integer mode, Integer status, Integer total, Date register_date, Date receive_date) {
        this.box_no = box_no;
        this.customer_idx = customer_idx;
        this.employee_idx = employee_idx;
        this.delivery_site_idx = delivery_site_idx;
        this.mode = mode;
        this.status = status;
        this.total = total;
        this.register_date = register_date;
        this.receive_date = receive_date;
    }
}
