package com.mhy.commodity.service;

import com.mhy.commodity.mapper.CommodityMapper;
import com.mhy.commodity.mapper.VendorMapper;
import com.mhy.commodity.pojo.Commodity;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.pojo.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("vendorServiceImpl")
public class VendorServiceImpl implements VendorService {

    private VendorMapper vendorMapper;
    private CommodityMapper commodityMapper;

    @Autowired
    public void setVendorMapper(VendorMapper vendorMapper) {
        this.vendorMapper = vendorMapper;
    }

    @Autowired
    public void setCommodityMapper(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    @Override
    public Page<Vendor> queryVendors(Map<String, Object> map) {
        //创建一分页对象
        Page<Vendor> vendorPage = new Page<>();
        //获取当前页数和当前页
        int currentPage = (int) map.get("currentPage");
        if(map.get("pageSize") != null){
            int pageSize = (int)map.get("pageSize");
            //设置每页数量
            vendorPage.setPageSize(pageSize);
        }

        //查询订单总的数量
        Integer pageTotalCount = vendorMapper.queryVendorsTotalCount(map);
        //设置总数
        vendorPage.setPageTotalCount(pageTotalCount);


        //求出总的页数
        int pageTotal = vendorPage.getPageTotalCount() / vendorPage.getPageSize();
        //判断边界条件
        if(vendorPage.getPageTotalCount() % vendorPage.getPageSize() > 0){
            pageTotal += 1;
        }

        //设置总的页数
        vendorPage.setPageTotal(pageTotal);
        //设置当前页数
        vendorPage.setCurrentPage(currentPage);
        //获取开始位置
        int begin = (vendorPage.getCurrentPage() - 1) * vendorPage.getPageSize();
        map.put("begin",begin);
        map.put("pageSize",vendorPage.getPageSize());

        //查询order
        List<Vendor> vendors = vendorMapper.queryVendors(map);
        //设置items
        vendorPage.setItems(vendors);


        return vendorPage;
    }

    @Override
    public void addVendor(Vendor vendor) {
        vendorMapper.addVendor(vendor);
    }

    @Override
    public boolean isVendorExistByVendorCode(String vendorCode) {
        return vendorMapper.isVendorExistByVendorCode(vendorCode) == 1;
    }

    @Override
    public Vendor queryVendorByVendorCode(String vendorCode) {
        return vendorMapper.queryVendorByVendorCode(vendorCode);
    }

    @Override
    public void updateVendor(Vendor vendor) {
        vendorMapper.updateVendor(vendor);
    }

    @Override
    public List<Commodity> searchCommodities(String searchCommodityName) {
        return commodityMapper.searchCommodities(searchCommodityName);
    }

    @Override
    public void addVendorWithCommodities(List<String> commoditiesCode, String vendorCode) {
        vendorMapper.addVendorWithCommodities(commoditiesCode,vendorCode);
    }

    @Override
    public void deleteVendorCommodity(String commodityCode, String vendorCode) {
        vendorMapper.deleteVendorCommodity(commodityCode,vendorCode);
    }


}
