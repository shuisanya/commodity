package com.mhy.commodity.service;


import com.mhy.commodity.pojo.Admin;
import com.mhy.commodity.pojo.Page;

import java.util.Map;


public interface AdminService {

//    分页查询按要求查询管理员集合
    Page<Admin> pageQueryAdminList(int begin,int pageSize);

//    搜索查询
    Page<Admin> pageSearchAdminList(Map<String,Object> searchMap);

//    判断用户名是否存在
    boolean isAdminCodeExist(String adminCode);

//    增加一个admin
    void addAdmin(Admin admin);

//    更新一个admin
    void updateAdminById(Admin admin);

//    删除一个admin
    void deleteAdminById(Integer id);

    Admin login(Map<String, Object> map);
}
