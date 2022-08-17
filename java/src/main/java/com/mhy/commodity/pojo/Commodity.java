package com.mhy.commodity.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component("commodity")
public class Commodity {
    //商品编号
    private String commodityCode;
    //商品名
    private String commodityName;
    //商品规格
    private String specification;
    //商品型号
    private String model;
    //商品计量单位
    private String unit;
    //商品市场价
    private BigDecimal marketPrice;
    //商品销售价
    private BigDecimal salesPrice;
    //商品的成本价
    private BigDecimal costPrice;
    //商品地址
    private String commodityUrl;
    //商品的简介
    private String commodityIntroduce;
    //商品库存量
    private int inventoryCount;

    //供货商
    private List<Vendor> vendors;
    //类别
    private List<Classify> classifies;
    //商品的品牌
    private Brand brand;

    public Commodity() {
    }

    public Commodity(String commodityCode, String commodityName, String specification, String model, String unit, BigDecimal marketPrice, BigDecimal salesPrice, BigDecimal costPrice, String commodityUrl, String commodityIntroduce, int inventoryCount, List<Vendor> vendors, Brand brand,List<Classify> classifies) {
        this.commodityCode = commodityCode;
        this.commodityName = commodityName;
        this.specification = specification;
        this.model = model;
        this.unit = unit;
        this.marketPrice = marketPrice;
        this.salesPrice = salesPrice;
        this.costPrice = costPrice;
        this.commodityUrl = commodityUrl;
        this.commodityIntroduce = commodityIntroduce;
        this.inventoryCount = inventoryCount;
        this.vendors = vendors;
        this.brand = brand;
        this.classifies = classifies;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getCommodityUrl() {
        return commodityUrl;
    }

    public void setCommodityUrl(String commodityUrl) {
        this.commodityUrl = commodityUrl;
    }

    public String getCommodityIntroduce() {
        return commodityIntroduce;
    }

    public void setCommodityIntroduce(String commodityIntroduce) {
        this.commodityIntroduce = commodityIntroduce;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Classify> getClassifies() {
        return classifies;
    }

    public void setClassifies(List<Classify> classifies) {
        this.classifies = classifies;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "commodityCode='" + commodityCode + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", specification='" + specification + '\'' +
                ", model='" + model + '\'' +
                ", unit='" + unit + '\'' +
                ", marketPrice=" + marketPrice +
                ", salesPrice=" + salesPrice +
                ", costPrice=" + costPrice +
                ", commodityUrl='" + commodityUrl + '\'' +
                ", commodityIntroduce='" + commodityIntroduce + '\'' +
                ", inventoryCount=" + inventoryCount +
                ", vendors=" + vendors +
                ", classifies=" + classifies +
                ", brand=" + brand +
                '}';
    }
}
