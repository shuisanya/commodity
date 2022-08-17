package com.mhy.commodity.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component("user")
public class User {

    //用户编码
    private String userCode;
    //用户姓名
    private String userName;
    //用户地址
    private String address;
    //用户电话
    private String phone;
    //点单
    private List<Order> orders;

    public User() {
    }

    public User(String userCode, String userName, String address, String phone, List<Order> orders) {
        this.userCode = userCode;
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.orders = orders;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", orders=" + orders +
                '}';
    }
}
