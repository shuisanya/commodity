package com.mhy.commodity.service;

import com.mhy.commodity.pojo.Commodity;
import com.mhy.commodity.pojo.Page;

import java.util.List;
import java.util.Map;

public interface CommodityService {

    Page<Commodity> queryCommodities(Map<String,Object> map);

    Map<String,Object> queryAllVendorsAndBrands();

    boolean isCommodityExistByCommodityCode(String commodityCode);

    void addCommodity(Commodity commodity, List<String> vendorCodes,List<Integer> classifyIds);

    Commodity queryCommodityByCommodityCode(String commodityCode);

    void updateCommodityByCommodityCode(Commodity commodity);

    void addCommodityWithVendors(List<String> vendorCodes, String commodityCode);

    void addCommodityWithClassifies(List<Integer> classifyIds, String commodityCode);

    void deleteCommodityWithVendor(String vendorCode, String commodityCode);

    void deleteCommodityWithClassify(Integer classifyId, String commodityCode);

    void deleteCommodityByCommodityCode(String commodityCode);
}
