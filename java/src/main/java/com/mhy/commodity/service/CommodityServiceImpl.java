package com.mhy.commodity.service;

import com.mhy.commodity.mapper.BrandMapper;
import com.mhy.commodity.mapper.ClassifyMapper;
import com.mhy.commodity.mapper.CommodityMapper;
import com.mhy.commodity.mapper.VendorMapper;
import com.mhy.commodity.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commodityServiceImpl")
public class CommodityServiceImpl implements CommodityService{

    private CommodityMapper commodityMapper;
    private BrandMapper brandMapper;
    private VendorMapper vendorMapper;
    private ClassifyMapper classifyMapper;

    @Autowired
    public void setCommodityMapper(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    @Autowired
    public void setBrandMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Autowired
    public void setVendorMapper(VendorMapper vendorMapper) {
        this.vendorMapper = vendorMapper;
    }

    @Autowired
    public void setClassifyMapper(ClassifyMapper classifyMapper) {
        this.classifyMapper = classifyMapper;
    }

    @Override
    public Page<Commodity> queryCommodities(Map<String, Object> map) {
        //创建一分页对象
        Page<Commodity> commodityPage = new Page<>();
        //获取当前页数和当前页
        int currentPage = (int) map.get("currentPage");

        if(map.get("pageSize") != null){
            int pageSize = (int)map.get("pageSize");
            //设置每页数量
            commodityPage.setPageSize(pageSize);
        }

        //查询商品的数量
        Integer pageTotalCount = commodityMapper.queryCommoditiesTotalCount(map);
        //设置总数
        commodityPage.setPageTotalCount(pageTotalCount);


        //求出总的页数
        int pageTotal = commodityPage.getPageTotalCount() / commodityPage.getPageSize();
        //判断边界条件
        if(commodityPage.getPageTotalCount() % commodityPage.getPageSize() > 0){
            pageTotal += 1;
        }

        //设置总的页数
        commodityPage.setPageTotal(pageTotal);
        //设置当前页数
        commodityPage.setCurrentPage(currentPage);
        //获取开始位置
        int begin = (commodityPage.getCurrentPage() - 1) * commodityPage.getPageSize();
        map.put("begin",begin);
        map.put("pageSize",commodityPage.getPageSize());

        //查询order
        List<Commodity> commodities = commodityMapper.queryCommodities(map);
        //设置items
        commodityPage.setItems(commodities);


        return commodityPage;
    }

    @Override
    public Map<String, Object> queryAllVendorsAndBrands() {
        Map<String,Object> map = new HashMap<>();
        List<Brand> brands = brandMapper.queryAllBrands();
        List<Vendor> vendors = vendorMapper.queryAllVendors();
        List<Classify> classifies = classifyMapper.queryAllClassifies();
        map.put("brands",brands);
        map.put("vendors",vendors);
        map.put("classifies",classifies);
        return map;
    }

    @Override
    public boolean isCommodityExistByCommodityCode(String commodityCode) {
        return commodityMapper.isCommodityExistByCommodityCode(commodityCode) == 1;
    }

    @Override
    public void addCommodity(Commodity commodity, List<String> vendorCodes ,List<Integer> classifyIds) {
        commodityMapper.addCommodity(commodity);
        if(!vendorCodes.get(0).equals("-1")){
            commodityMapper.addCommodityAndVendors(commodity.getCommodityCode(),vendorCodes);
        }
        if(classifyIds.get(0) != -1){
            commodityMapper.addCommodityAndClassifies(commodity.getCommodityCode(),classifyIds);
        }

    }

    @Override
    public Commodity queryCommodityByCommodityCode(String commodityCode) {
        return commodityMapper.queryCommodityByCommodityCode(commodityCode);
    }

    @Override
    public void updateCommodityByCommodityCode(Commodity commodity) {
        commodityMapper.updateCommodityByCommodityCode(commodity);
    }

    @Override
    public void addCommodityWithVendors(List<String> vendorCodes, String commodityCode) {
        commodityMapper.addCommodityAndVendors(commodityCode,vendorCodes);
    }

    @Override
    public void addCommodityWithClassifies(List<Integer> classifyIds, String commodityCode) {
        commodityMapper.addCommodityAndClassifies(commodityCode,classifyIds);
    }

    @Override
    public void deleteCommodityWithVendor(String vendorCode, String commodityCode) {
        commodityMapper.deleteCommodityWithVendor(vendorCode,commodityCode);
    }

    @Override
    public void deleteCommodityWithClassify(Integer classifyId, String commodityCode) {
        commodityMapper.deleteCommodityWithClassify(classifyId,commodityCode);
    }

    @Override
    public void deleteCommodityByCommodityCode(String commodityCode) {
        commodityMapper.deleteCommodityWithVendor(null,commodityCode);
        commodityMapper.deleteCommodityWithClassify(null,commodityCode);
        commodityMapper.deleteCommodityByCommodityCode(commodityCode);
    }
}
