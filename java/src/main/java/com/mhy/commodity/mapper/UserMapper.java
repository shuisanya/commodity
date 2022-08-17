package com.mhy.commodity.mapper;

import com.mhy.commodity.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //按要求查询用户
    List<User> queryUserList(Map<String,Object> map);
    //查询按要求的用户数量
    Integer queryUsersTotalCount(@Param("searchUserName") String searchUserName);

    Integer queryAllCounts();

}
