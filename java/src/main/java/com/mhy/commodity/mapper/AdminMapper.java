package com.mhy.commodity.mapper;

import com.mhy.commodity.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdminMapper {

//    按要求查询管理员的集合
    List<Admin> queryAdminList(@Param("begin") int begin, @Param("pageSize") int pageSize);

//    搜索查询管理员的集合
    List<Admin> searchAdminList(@Param("map") Map<String,Object> map);

//    查询总的管理员数量
    Integer queryAdminsTotalCount(@Param("adminName") String adminName,@Param("adminRole") String adminRole);

//    判断用户名是否存在
    String queryAdminCodeExist(@Param("adminCode") String adminCode);

//   添加一个admin
    void addAdmin(Admin admin);

//    更新一个admin
    void updateAdminById(Admin admin);

//    删除一个admin
    void deleteAdminById(@Param("id") Integer id);

    Integer queryAllCounts();

    Admin login(Map<String, Object> map);
}
