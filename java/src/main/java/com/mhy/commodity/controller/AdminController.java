package com.mhy.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhy.commodity.pojo.Admin;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin // 这个可以解决跨域问题
@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    @Qualifier(value = "adminServiceImpl")
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/login/{adminStr}")
    public void login(HttpServletResponse resp,
                      HttpServletRequest request,
                      @PathVariable String adminStr) throws IOException {
        Map<String,Object> map = JSONObject.parseObject(adminStr, (Type)Map.class);
        Admin admin = adminService.login(map);

        Map<String,Object> map1 = new HashMap<>();

        String msg = "";

        if(admin != null){
            map1.put("admin",admin.getAdminCode());

        }else {
            msg = "账号或密码错误！！！";
            map1.put("msg",msg);
        }

        String toJSONString = JSON.toJSONString(map1);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);

    }

    /**
     *  根据页码查询一个admin
     * @param resp http响应给前端数据
     * @param currentPage 当前页码
     * @param pageSize  当前每页的页数
     * @throws IOException IO异常
     */
    @GetMapping("/queryAdmins/{currentPage}/{pageSize}")
    public void queryAdminList(HttpServletResponse resp,
                               @PathVariable Integer currentPage,
                               @PathVariable Integer pageSize) throws IOException
    {
        Page<Admin> page = adminService.pageQueryAdminList(currentPage, pageSize);

        String jsonString = JSON.toJSONString(page);
        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(jsonString);
    }

    /**
     *  搜索查询数据
     * @param data 前端的JSON字符串
     * @param resp http响应给前端数据
     * @throws IOException IO异常
     */
    @GetMapping("/searchAdmins/{data}")
    public void searchAdminList(@PathVariable String data,
                                HttpServletResponse resp) throws IOException
    {
        HashMap<String,Object> searchMap = JSONObject.parseObject(data, (Type) Map.class);

        Page<Admin> page = adminService.pageSearchAdminList(searchMap);
        String jsonString = JSON.toJSONString(page);
        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(jsonString);
    }

    /**
     * 判断用户是否存在
     * @param adminCode  用户名
     * @param resp http响应给前端数据
     * @throws IOException IO异常
     */
    @GetMapping("/isAdminCodeExist/{adminCode}")
    public void isAdminCodeExist(@PathVariable String adminCode,
                                 HttpServletResponse resp) throws IOException
    {
        boolean exist = adminService.isAdminCodeExist(adminCode);

        resp.setContentType("text/html; charset=UTF-8");
        String isExist = JSON.toJSONString(exist);

        resp.getWriter().write(isExist);
    }

    /**
     * 增加一个admin
     * @param adminString 前端JSON的数据
     * @param resp http响应给前端数据
     * @throws IOException IO异常
     */
    @PostMapping("/addAdmin/{adminString}")
    public void addAdmin(@PathVariable String adminString,
                         HttpServletResponse resp) throws IOException
    {

//        System.out.println(adminString);

        Admin admin = JSONObject.parseObject(adminString, Admin.class);

        adminService.addAdmin(admin);

        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write("添加成功");
    }

    /**
     * 更新一个admin
     * @param adminString 前端JSON字符串数据
     * @param resp http响应给前端数据
     * @throws IOException IO异常
     */
    @PostMapping("/updateAdminById/{adminString}")
    public void updateAdminById(@PathVariable String adminString,
                         HttpServletResponse resp) throws IOException
    {

//        System.out.println(adminString);

        Admin admin = JSONObject.parseObject(adminString, Admin.class);

        adminService.updateAdminById(admin);
//        System.out.println(admin);

        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write("更新成功");
    }

    /**
     * 删除一个 admin
     * @param id 删除admin的id
     * @param resp http响应给前端数据
     * @throws IOException IO异常
     */
    @GetMapping("/deleteAdminById/{id}")
    public void deleteAdminById(@PathVariable Integer id,
                         HttpServletResponse resp) throws IOException
    {

        adminService.deleteAdminById(id);
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write("删除成功");
    }




}
