package com.mhy.commodity.mapper;

import com.mhy.commodity.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    /**
     * 根据查询条件查询品牌集合
     * @param map 条件的map
     * @return 品牌的集合
     */
    List<Brand> queryBrands(Map<String,Object> map);

    /**
     * 根据查询条件查询品牌集合的数量
     * @param map 相关信息的map
     * @return 返回数量
     */
    Integer queryBrandsTotalCount(Map<String,Object> map);

    /**
     * 判断该品牌的编号是否存在
     * @param brandCode 品牌的编号
     * @return 如果存在返回1，不存在返回0
     */
    Integer isBrandExistByBrandCode(@Param("brandCode") String brandCode);

    /**
     * 增加一个品牌
     * @param brand 品牌的对象
     */
    void addBrand(Brand brand);

    /**
     * 查询一个品牌的详细信息
     * @param brandCode 品牌的编号
     * @return 返回一个品牌对象
     */
    Brand queryBrandByBrandCode(@Param("brandCode") String brandCode);

    /**
     * 查询所有的品牌
     * @return 所有的品牌集合
     */
    List<Brand> queryAllBrands();

    Integer queryAllCounts();
}
