package com.mhy.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.pojo.User;
import com.mhy.commodity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

@CrossOrigin // 这个可以解决跨域问题
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    @Qualifier(value = "userServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/queryUsers/{data}")
    public void queryUsers(HttpServletResponse resp,
                            @PathVariable String data) throws IOException {

        Map<String,Object> map = JSONObject.parseObject(data, (Type)Map.class);

        Page<User> userPage = userService.queryUserList(map);

        String stringUserPage = JSON.toJSONString(userPage);
        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(stringUserPage);

    }
}
