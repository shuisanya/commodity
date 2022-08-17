package com.mhy.commodity.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhy.commodity.pojo.Commodity;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.pojo.Vendor;
import com.mhy.commodity.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/vendor")
public class VendorController {

    private VendorService vendorService;

    @Autowired
    @Qualifier("vendorServiceImpl")
    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/queryVendors/{data}")
    public void queryVendors(HttpServletResponse resp,
                             @PathVariable String data) throws IOException {

        Map<String,Object> map = JSONObject.parseObject(data, (Type)Map.class);
        System.out.println(map);

        Page<Vendor> vendorPage = vendorService.queryVendors(map);

        String toJSONString = JSON.toJSONString(vendorPage);
        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }

    @PostMapping("/addVendor/{data}")
    public void addVendor(HttpServletResponse resp,
                          @PathVariable String data) throws IOException {
        Vendor vendor = JSONObject.parseObject(data, Vendor.class);

        vendorService.addVendor(vendor);
        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("添加成功");

    }

    @GetMapping("/isVendorExistByVendorCode/{vendorCode}")
    public void isVendorExistByVendorCode(HttpServletResponse resp,
                                          @PathVariable String vendorCode) throws IOException {

        boolean isVendorCodeExist = vendorService.isVendorExistByVendorCode(vendorCode);

        String toJSONString = JSON.toJSONString(isVendorCodeExist);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);

    }


    @GetMapping("/queryVendorByVendorCode/{vendorCode}")
    public void queryVendorByVendorCode(HttpServletResponse resp,
                                        @PathVariable String vendorCode) throws IOException {
        Vendor vendor = vendorService.queryVendorByVendorCode(vendorCode);

        String toJSONString = JSON.toJSONString(vendor);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }

    @PostMapping("/updateVendor/{data}")
    public void updateVendor(HttpServletResponse resp,
                             @PathVariable String data) throws IOException {
        Vendor vendor = JSONObject.parseObject(data, Vendor.class);

        vendorService.updateVendor(vendor);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("更新成功！！！");
    }

    @GetMapping("/searchCommodities/{searchCommodityName}")
    public void searchCommodities(HttpServletResponse resp,
                                  @PathVariable String searchCommodityName) throws IOException {
        List<Commodity> commodities = vendorService.searchCommodities(searchCommodityName);

        String toJSONString = JSON.toJSONString(commodities);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }

    @GetMapping("/addVendorCommodities/{data}/{vendorCode}")
    public void addVendorWithCommodities(HttpServletResponse resp,
                                         @PathVariable List<String> data,
                                         @PathVariable String vendorCode) throws IOException {

        vendorService.addVendorWithCommodities(data,vendorCode);
        System.out.println(data);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("保存成功");
    }

    @GetMapping("/deleteVendorCommodity/{commodityCode}/{vendorCode}")
    public void deleteVendorCommodity(HttpServletResponse resp,
                                      @PathVariable String commodityCode,
                                      @PathVariable String vendorCode) throws IOException {

        vendorService.deleteVendorCommodity(commodityCode,vendorCode);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("删除成功");
    }

}
