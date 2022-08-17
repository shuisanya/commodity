package com.mhy.commodity.service;

import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.pojo.User;
import java.util.Map;


public interface UserService {

    Page<User> queryUserList(Map<String,Object> map);

}
