package com.mhy.commodity.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("admin")
public class Admin {
//    id自增
    private int id;
//    管理员账号
    private String adminCode;
//    管理员密码
    private String adminPassword;
//    管理员姓名
    private String adminName;
//    管理员性别 1：男  2：女
    private int sex;
//    管理员生日
    private Date birthday;
//    管理员手机
    private String phone;
//    管理员地址
    private String address;
//    管理员职位或者角色
    private String adminRole;

    public Admin() {
    }

    public Admin(int id, String adminCode, String adminPassword, String adminName, int sex, Date birthday, String phone, String address, String adminRole) {
        this.id = id;
        this.adminCode = adminCode;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.adminRole = adminRole;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminCode='" + adminCode + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminName='" + adminName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", adminRole='" + adminRole + '\'' +
                '}';
    }
}
