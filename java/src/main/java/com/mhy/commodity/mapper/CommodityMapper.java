package com.mhy.commodity.mapper;

import com.mhy.commodity.pojo.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommodityMapper {

    /**
     * 根据商品的名称搜索商品的信息 在vendor中使用的
     * @param searchCommodityName 商品的名称
     * @return 返回商品的集合
     */
    List<Commodity> searchCommodities(@Param("searchCommodityName") String searchCommodityName);

    /**
     * 根据相关信息查询商品
     * @param map 商品相关信息的map
     * @return 商品的集合
     */
    List<Commodity> queryCommodities(Map<String,Object> map);

    /**
     * 根据相关信息查询商品的数量
     * @param map 商品相关信息的map
     * @return 商品的数量
     */
    Integer queryCommoditiesTotalCount(Map<String,Object> map);

    /**
     * 判断商品编号是否存在
     * @param commodityCode 商品编号
     * @return 返回1表示存在 0表示不存在
     */
    Integer isCommodityExistByCommodityCode(@Param("commodityCode") String commodityCode);

    /**
     * 增加一个商品
     * @param commodity 商品对象
     */
    void addCommodity(Commodity commodity);

    void addCommodityAndVendors(@Param("commodityCode") String commodityCode,
                                @Param("vendorCodes") List<String> vendorCodes);

    void addCommodityAndClassifies(@Param("commodityCode") String commodityCode,
                                   @Param("classifyIds") List<Integer> classifyIds);

    /**
     * 根据商品编号查询一个商品信息
     * @param commodityCode 商品的编号
     * @return 一个商品
     */
    Commodity queryCommodityByCommodityCode(@Param("commodityCode") String commodityCode);

    /**
     * 更新一个商品
     * @param commodity 商品对象
     */
    void updateCommodityByCommodityCode(Commodity commodity);

    /**
     * 商品中删除一个供应商
     * @param vendorCode 供应商编号
     * @param commodityCode 商品编号
     */
    void deleteCommodityWithVendor(@Param("vendorCode") String vendorCode,
                               @Param("commodityCode") String commodityCode);   /**
     * 商品中删除一个类别
     * @param classifyId 类别id
     * @param commodityCode 商品编号
     */
    void deleteCommodityWithClassify(@Param("classifyId") Integer classifyId,
                               @Param("commodityCode") String commodityCode);

    /**
     * 删除一个商品
     * @param commodityCode 商品编号
     */
    void deleteCommodityByCommodityCode(@Param("commodityCode") String commodityCode);

    Integer queryAllCounts();
}
