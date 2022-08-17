package com.mhy.commodity.service;

import com.mhy.commodity.mapper.OrderMapper;
import com.mhy.commodity.pojo.Order;
import com.mhy.commodity.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public Page<Order> queryOrders(Map<String, Object> map) {
        //创建一分页对象
        Page<Order> orderPage = new Page<>();
        //获取当前页数和当前页
        int currentPage = (int) map.get("currentPage");
        if(map.get("pageSize") != null){
            int pageSize = (int)map.get("pageSize");
            //设置每页数量
            orderPage.setPageSize(pageSize);
        }

        //查询订单总的数量
        Integer pageTotalCount = orderMapper.queryOrdersCount(map);
        //设置总数
        orderPage.setPageTotalCount(pageTotalCount);


        //求出总的页数
        int pageTotal = orderPage.getPageTotalCount() / orderPage.getPageSize();
        //判断边界条件
        if(orderPage.getPageTotalCount() % orderPage.getPageSize() > 0){
            pageTotal += 1;
        }

        //设置总的页数
        orderPage.setPageTotal(pageTotal);
        //设置当前页数
        orderPage.setCurrentPage(currentPage);
        //获取开始位置
        int begin = (currentPage - 1) * orderPage.getPageSize();
        map.put("begin",begin);
        map.put("pageSize",orderPage.getPageSize());

        //查询order
        List<Order> orders = orderMapper.queryOrders(map);
        //设置items
        orderPage.setItems(orders);


        return orderPage;
    }

    @Override
    public Order queryOrderDetailsByOrderCode(String orderCode) {
        return orderMapper.queryOrderDetailsByOrderCode(orderCode);
    }

    @Override
    public void shippingOrderDetail(String detailCode) {
        orderMapper.shippingOrderDetailByDetailCode(detailCode,new Date());
    }
}
