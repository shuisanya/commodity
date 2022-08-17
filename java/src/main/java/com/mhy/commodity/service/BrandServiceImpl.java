package com.mhy.commodity.service;

import com.mhy.commodity.mapper.BrandMapper;
import com.mhy.commodity.pojo.Brand;
import com.mhy.commodity.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("brandServiceImpl")
public class BrandServiceImpl implements BrandService{

    private BrandMapper brandMapper;

    @Autowired
    public void setBrandMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Override
    public Page<Brand> queryBrands(Map<String, Object> map) {
        //创建一分页对象
        Page<Brand> brandPage = new Page<>();
        //获取当前页数和当前页
        int currentPage = (int) map.get("currentPage");
        if(map.get("pageSize") != null){
            int pageSize = (int)map.get("pageSize");
            //设置每页数量
            brandPage.setPageSize(pageSize);
        }

        //查询订单总的数量
        Integer pageTotalCount = brandMapper.queryBrandsTotalCount(map);
        //设置总数
        brandPage.setPageTotalCount(pageTotalCount);


        //求出总的页数
        int pageTotal = brandPage.getPageTotalCount() / brandPage.getPageSize();
        //判断边界条件
        if(brandPage.getPageTotalCount() % brandPage.getPageSize() > 0){
            pageTotal += 1;
        }

        //设置总的页数
        brandPage.setPageTotal(pageTotal);
        //设置当前页数
        brandPage.setCurrentPage(currentPage);
        //获取开始位置
        int begin = (brandPage.getCurrentPage() - 1) * brandPage.getPageSize();
        map.put("begin",begin);
        map.put("pageSize",brandPage.getPageSize());

        //查询order
        List<Brand> brands = brandMapper.queryBrands(map);
        //设置items
        brandPage.setItems(brands);


        return brandPage;
    }

    @Override
    public boolean isBrandExistByBrandCode(String brandCode) {
        return brandMapper.isBrandExistByBrandCode(brandCode) == 1;
    }

    @Override
    public void addBrand(Brand brand) {
        brandMapper.addBrand(brand);
    }

    @Override
    public Brand queryBrandByBrandCode(String brandCode) {
        return brandMapper.queryBrandByBrandCode(brandCode);
    }


}
