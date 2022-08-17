package com.mhy.commodity.controller;


import com.alibaba.fastjson.JSON;
import com.mhy.commodity.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/home")
@CrossOrigin // 这个可以解决跨域问题
public class HomeController {

    private HomeService homeService;

    @Autowired
    public void setHomeService(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/queryAllCounts")
    public void queryAllCounts(HttpServletResponse resp) throws IOException {

        Map<String, Integer> allCounts = homeService.queryAllCounts();

        String toJSONString = JSON.toJSONString(allCounts);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }
}
