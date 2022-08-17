package com.mhy.commodity.mapper;

import com.mhy.commodity.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderMapper {
    /**
     * 根据用户编码查询他的所有订单信息
     * @param userCode 用户编码
     * @return 返回的是一个订单集合
     */
    List<Order> queryAllOrderByUserCode(@Param("userCode") String userCode);

    /**
     * 根据相关信息查询订单
     * @param map 相关信息的map  有currentPage:页码   pageSize:每页数量   搜索的一些相关信息 日期  金额等等
     * @return 返回欧冠订单集合
     */
    List<Order> queryOrders(Map<String,Object> map);

    /**
     * 根据相关信息查询订单的总数
     * @param map 相关信息map
     * @return 返回数量
     */
    Integer queryOrdersCount(Map<String,Object> map);

    /**
     * 根据一个订单号查它的全部订单明细
     * @param orderCode 订单编号
     * @return 返回订单明细的集合
     */
    Order queryOrderDetailsByOrderCode(@Param("orderCode") String orderCode);

    /**
     * 根据订单明细号发送一个订单
     * @param detailCode 订单明细
     */
    void shippingOrderDetailByDetailCode(@Param("detailCode") String detailCode,
                                         @Param("shippingTime") Date shippingTime);

    Integer queryAllCounts();
}
