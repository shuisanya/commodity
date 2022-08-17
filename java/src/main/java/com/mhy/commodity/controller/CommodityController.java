package com.mhy.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhy.commodity.pojo.Brand;
import com.mhy.commodity.pojo.Commodity;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@CrossOrigin // 这个可以解决跨域问题
@Controller
@RequestMapping("/commodity")
public class CommodityController {

    private CommodityService commodityService;

    @Autowired
    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @GetMapping("/queryCommodities/{data}")
    public void queryCommodities(HttpServletResponse resp,
                                 @PathVariable String data) throws IOException {
        Map<String,Object> map = JSONObject.parseObject(data, (Type)Map.class);
        Page<Commodity> commodityPage = commodityService.queryCommodities(map);

        String toJSONString = JSON.toJSONString(commodityPage);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }

    @GetMapping("/queryAllVendorsAndBrands")
    public void queryAllVendorsAndBrands(HttpServletResponse resp) throws IOException {
        Map<String, Object> map = commodityService.queryAllVendorsAndBrands();

        String toJSONString = JSON.toJSONString(map);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }

    @PostMapping("/addCommodity/{commodity}/{vendorCodes}/{classifyIds}")
    public void addCommodity(HttpServletResponse resp,
                             @PathVariable String commodity,
                             @PathVariable List<String> vendorCodes,
                             @PathVariable List<Integer> classifyIds) throws IOException {
        Commodity commodity1 = JSONObject.parseObject(commodity, Commodity.class);
        Map<String,Object> map = JSONObject.parseObject(commodity, (Type)Map.class);
        commodity1.setBrand(new Brand());
        if(map.get("brandCode").equals("")){
            commodity1.getBrand().setBrandCode(null);
        }else{
            commodity1.getBrand().setBrandCode((String) map.get("brandCode"));
        }

        commodityService.addCommodity(commodity1,vendorCodes,classifyIds);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("添加成功");
    }

    @GetMapping("/isCommodityExistByCommodityCode/{commodityCode}")
    public void isCommodityExistByCommodityCode(HttpServletResponse resp,
                                                @PathVariable String commodityCode) throws IOException {

        boolean isCommodityCode = commodityService.isCommodityExistByCommodityCode(commodityCode);

        String toJSONString = JSON.toJSONString(isCommodityCode);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);

    }

    @GetMapping("/queryCommodityByCommodityCode/{commodityCode}")
    public void queryCommodityByCommodityCode(HttpServletResponse resp,
                                              @PathVariable String commodityCode) throws IOException {
        Commodity commodity = commodityService.queryCommodityByCommodityCode(commodityCode);

        String toJSONString = JSON.toJSONString(commodity);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }

    @PostMapping("/updateCommodityByCommodityCode/{strCommodity}")
    public void updateCommodityByCommodityCode(HttpServletResponse resp,
                                               @PathVariable String strCommodity) throws IOException {
        Commodity commodity = JSONObject.parseObject(strCommodity, Commodity.class);
        System.out.println(commodity);

        commodityService.updateCommodityByCommodityCode(commodity);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("更新成功");
    }

    @GetMapping("/addCommodityVendors/{vendorCodes}/{commodityCode}")
    public void addCommodityWithVendors(HttpServletResponse resp,
                                        @PathVariable List<String> vendorCodes,
                                        @PathVariable String commodityCode) throws IOException {
        commodityService.addCommodityWithVendors(vendorCodes,commodityCode);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("保存成功");
    }

    @GetMapping("/addCommodityWithClassifies/{classifyIds}/{commodityCode}")
    public void addCommodityWithClassifies(HttpServletResponse resp,
                                        @PathVariable List<Integer> classifyIds,
                                        @PathVariable String commodityCode) throws IOException {
        commodityService.addCommodityWithClassifies(classifyIds,commodityCode);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("保存成功");
    }

    @GetMapping("/deleteCommodityVendor/{vendorCode}/{commodityCode}")
    public void deleteCommodityVendor(HttpServletResponse resp,
                                      @PathVariable String vendorCode,
                                      @PathVariable String commodityCode) throws IOException {
        commodityService.deleteCommodityWithVendor(vendorCode,commodityCode);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("删除成功");
    }

    @GetMapping("/deleteCommodityClassify/{classifyId}/{commodityCode}")
    public void deleteCommodityClassify(HttpServletResponse resp,
                                      @PathVariable Integer classifyId,
                                      @PathVariable String commodityCode) throws IOException {
        commodityService.deleteCommodityWithClassify(classifyId,commodityCode);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("删除成功");
    }

    @GetMapping("/deleteCommodityByCommodityCode/{commodityCode}")
    public void deleteCommodityByCommodityCode(HttpServletResponse resp,
                                               @PathVariable String commodityCode) throws IOException {
        commodityService.deleteCommodityByCommodityCode(commodityCode);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("删除成功");
    }

}
