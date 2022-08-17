package com.mhy.commodity.service;

import com.mhy.commodity.pojo.Commodity;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.pojo.Vendor;

import java.util.List;
import java.util.Map;

public interface VendorService {

    Page<Vendor> queryVendors(Map<String,Object> map);

    void addVendor(Vendor vendor);

    boolean isVendorExistByVendorCode(String vendorCode);

    Vendor queryVendorByVendorCode(String vendorCode);

    void updateVendor(Vendor vendor);

    List<Commodity> searchCommodities(String searchCommodityName);

    void addVendorWithCommodities(List<String> commoditiesCode,String vendorCode);

    void deleteVendorCommodity(String commodityCode, String vendorCode);
}
