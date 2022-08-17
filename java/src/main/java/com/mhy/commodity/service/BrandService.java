package com.mhy.commodity.service;

import com.mhy.commodity.pojo.Brand;
import com.mhy.commodity.pojo.Page;

import java.util.Map;

public interface BrandService {

    Page<Brand> queryBrands(Map<String,Object> map);

    boolean isBrandExistByBrandCode(String brandCode);

    void addBrand(Brand brand);

    Brand queryBrandByBrandCode(String brandCode);
}
