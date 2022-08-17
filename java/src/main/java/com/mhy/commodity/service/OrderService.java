package com.mhy.commodity.service;

import com.mhy.commodity.pojo.Order;
import com.mhy.commodity.pojo.Page;


import java.util.Map;

public interface OrderService {

    Page<Order> queryOrders(Map<String,Object> map);

    Order queryOrderDetailsByOrderCode(String orderCode);

    void shippingOrderDetail(String detailCode);
}
