package com.mhy.commodity.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhy.commodity.pojo.Order;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

@CrossOrigin // 这个可以解决跨域问题
@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    @Qualifier("orderServiceImpl")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/queryOrders/{data}")
    public void queryOrders(HttpServletResponse resp,
                            @PathVariable String data) throws IOException {

        Map<String,Object> map = JSONObject.parseObject(data,(Type) Map.class);

        Page<Order> orderPage = orderService.queryOrders(map);

        String toJSONString = JSON.toJSONString(orderPage);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);

    }

    @GetMapping("/queryOrder/{orderCode}")
    public void queryOrder(HttpServletResponse resp,
                                  @PathVariable String orderCode) throws IOException {
        Order order = orderService.queryOrderDetailsByOrderCode(orderCode);

        String toJSONString = JSON.toJSONString(order);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }

    @GetMapping("/shippingOrderDetail/{detailCode}")
    public void shippingOrderDetail(HttpServletResponse resp,
                                    @PathVariable String detailCode) throws IOException {
        orderService.shippingOrderDetail(detailCode);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("发货成功");
    }
}
