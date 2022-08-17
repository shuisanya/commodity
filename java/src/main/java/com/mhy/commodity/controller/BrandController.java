package com.mhy.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhy.commodity.pojo.Brand;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

@CrossOrigin // 这个可以解决跨域问题
@Controller
@RequestMapping("/brand")
public class BrandController {
    private BrandService brandService;

    @Autowired
    @Qualifier("brandServiceImpl")
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/queryBrands/{data}")
    public void queryBrands(HttpServletResponse resp,
                            @PathVariable String data) throws IOException {

        Map<String,Object> map = JSONObject.parseObject(data, (Type)Map.class);

        Page<Brand> brandPage = brandService.queryBrands(map);

        String toJSONString = JSON.toJSONString(brandPage);
        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }

    @GetMapping("/isBrandExistByBrandCode/{brandCode}")
    public void isBrandExistByBrandCode(HttpServletResponse resp,
                                        @PathVariable String brandCode) throws IOException {
        boolean brandExistByBrandCode = brandService.isBrandExistByBrandCode(brandCode);

        String toJSONString = JSON.toJSONString(brandExistByBrandCode);
        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }

    @PostMapping("/addBrand/{data}")
    public void addBrand(HttpServletResponse resp,
                         @PathVariable String data) throws IOException {
        Brand brand = JSONObject.parseObject(data, Brand.class);

        brandService.addBrand(brand);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write("添加成功");
    }

    @GetMapping("/queryBrandByBrandCode/{brandCode}")
    public void queryBrandByBrandCode(HttpServletResponse resp,
                                      @PathVariable String brandCode) throws IOException {

        Brand brand = brandService.queryBrandByBrandCode(brandCode);

        String toJSONString = JSON.toJSONString(brand);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(toJSONString);
    }

}
