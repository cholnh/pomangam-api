package com.mrporter.pomangam._bases.utils.bootpay.model.request;

import java.util.ArrayList;
import java.util.List;

public class SubscribeBillingReserve {
    public String billing_key;
    public String item_name;
    public long price;
    public String order_id;
    public String pg;

    public String scheduler_type;
    public long execute_at;
    public String feedback_url;
    public List<Item> items = new ArrayList<>(); // 통계를 위해 사용됨
}
