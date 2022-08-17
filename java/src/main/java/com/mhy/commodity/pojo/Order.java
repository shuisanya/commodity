package com.mhy.commodity.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component("order")
public class Order {
    //订单编号
    private String orderCode;
    //订单邮编
    private String zip;
    //购买订单时间
    private Date orderTime;
    //订单金额
    private BigDecimal orderAmount;
    //订单明细
    private List<OrderDetails> orderDetails;

    public Order() {
    }

    public Order(String orderCode, String zip, Date orderTime, BigDecimal orderAmount, List<OrderDetails> orderDetails) {
        this.orderCode = orderCode;
        this.zip = zip;
        this.orderTime = orderTime;
        this.orderAmount = orderAmount;
        this.orderDetails = orderDetails;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderCode='" + orderCode + '\'' +
                ", zip='" + zip + '\'' +
                ", orderTime=" + orderTime +
                ", orderAmount=" + orderAmount +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
