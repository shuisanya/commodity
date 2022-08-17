package com.mhy.commodity.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component("orderDetails")
public class OrderDetails {
    //订单明细编号
    private String DetailsCode;
    //数量
    private Integer count;
    //单价
    private BigDecimal unitPrice;
    //该明细的总金额
    private BigDecimal amount;
    //订单是否发货  0表示未发货  1表示运输中  2表示已签收
    private Integer orderState;
    //发货时间
    private Date shippingTime;
    //商品信息
    private Commodity commodity;

    public OrderDetails() {
    }

    public OrderDetails(String detailsCode, Integer count, BigDecimal unitPrice, BigDecimal amount, Integer orderState, Date shippingTime, Commodity commodity) {
        DetailsCode = detailsCode;
        this.count = count;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.orderState = orderState;
        this.shippingTime = shippingTime;
        this.commodity = commodity;
    }

    public String getDetailsCode() {
        return DetailsCode;
    }

    public void setDetailsCode(String detailsCode) {
        DetailsCode = detailsCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "DetailsCode='" + DetailsCode + '\'' +
                ", count=" + count +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", orderState=" + orderState +
                ", shippingTime=" + shippingTime +
                ", commodity=" + commodity +
                '}';
    }
}
