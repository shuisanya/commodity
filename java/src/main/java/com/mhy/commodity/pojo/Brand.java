package com.mhy.commodity.pojo;

import java.util.List;

public class Brand {

    //品牌编号
    private String brandCode;
    //品牌名称
    private String brandName;
    //名牌地址
    private String brandUrl;
    //品牌图片地址
    private String brandImgUrl;

    //属于该品牌的商品信息
    private List<Commodity> commodities;

    public Brand() {
    }

    public Brand(String brandCode, String brandName, String brandUrl, String brandImgUrl, List<Commodity> commodities) {
        this.brandCode = brandCode;
        this.brandName = brandName;
        this.brandUrl = brandUrl;
        this.brandImgUrl = brandImgUrl;
        this.commodities = commodities;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl;
    }

    public String getBrandImgUrl() {
        return brandImgUrl;
    }

    public void setBrandImgUrl(String brandImgUrl) {
        this.brandImgUrl = brandImgUrl;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandCode='" + brandCode + '\'' +
                ", brandName='" + brandName + '\'' +
                ", brandUrl='" + brandUrl + '\'' +
                ", brandImgUrl='" + brandImgUrl + '\'' +
                ", commodities=" + commodities +
                '}';
    }
}
