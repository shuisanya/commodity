package com.mhy.commodity.service;

import com.mhy.commodity.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("homeServiceImpl")
public class HomeServiceImpl implements HomeService{
    private AdminMapper adminMapper;
    private UserMapper userMapper;
    private OrderMapper orderMapper;
    private CommodityMapper commodityMapper;
    private VendorMapper vendorMapper;
    private BrandMapper brandMapper;
    private ClassifyMapper classifyMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setCommodityMapper(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    @Autowired
    public void setVendorMapper(VendorMapper vendorMapper) {
        this.vendorMapper = vendorMapper;
    }

    @Autowired
    public void setBrandMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Autowired
    public void setClassifyMapper(ClassifyMapper classifyMapper) {
        this.classifyMapper = classifyMapper;
    }

    @Override
    public Map<String, Integer> queryAllCounts() {
        Map<String,Integer> map = new HashMap<>();

        map.put("adminCounts",adminMapper.queryAllCounts());
        map.put("userCounts",userMapper.queryAllCounts());
        map.put("brandCounts",brandMapper.queryAllCounts());
        map.put("vendorCounts",vendorMapper.queryAllCounts());
        map.put("orderCounts",orderMapper.queryAllCounts());
        map.put("classifyCounts",classifyMapper.queryAllCounts());
        map.put("commodityCounts",commodityMapper.queryAllCounts());

        return map;
    }
}
