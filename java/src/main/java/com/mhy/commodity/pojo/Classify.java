package com.mhy.commodity.pojo;

public class Classify {

    private Integer classifyId;
    private String name;
    private String imgUrl;

    public Classify() {
    }

    public Classify(Integer classifyId, String name, String imgUrl) {
        this.classifyId = classifyId;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "classifyId=" + classifyId +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
