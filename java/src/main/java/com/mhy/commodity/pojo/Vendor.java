package com.mhy.commodity.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component("vendor")
public class Vendor {
    //供货商编号
    private String vendorCode;
    //供货商名
    private String vendorName;
    //供货商负责人
    private String contact;
    //供货商电话
    private String phone;
    //供货商简介
    private String briefIntroduction;
    //供货商官网
    private String vendorUrl;
    //供应的商品信息
    private List<Commodity> commodities;

    public Vendor() {
    }

    public Vendor(String vendorCode, String vendorName, String contact, String phone, String vendorUrl, String briefIntroduction, List<Commodity> commodities) {
        this.vendorCode = vendorCode;
        this.vendorName = vendorName;
        this.contact = contact;
        this.phone = phone;
        this.vendorUrl = vendorUrl;
        this.briefIntroduction = briefIntroduction;
        this.commodities = commodities;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

    public String getVendorUrl() {
        return vendorUrl;
    }

    public void setVendorUrl(String vendorUrl) {
        this.vendorUrl = vendorUrl;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "vendorCode='" + vendorCode + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", briefIntroduction='" + briefIntroduction + '\'' +
                ", vendorUrl='" + vendorUrl + '\'' +
                ", commodities=" + commodities +
                '}';
    }
}
