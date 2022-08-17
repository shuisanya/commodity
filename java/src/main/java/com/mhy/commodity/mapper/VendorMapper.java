package com.mhy.commodity.mapper;

import com.mhy.commodity.pojo.Commodity;
import com.mhy.commodity.pojo.Vendor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VendorMapper {
    /**
     * 根据查询条件查询供货商集合
     * @param map 条件的map
     * @return 供货商集合
     */
    List<Vendor> queryVendors(Map<String,Object> map);

    /**
     * 根据查询条件查询供货商的总数
     * @param map 条件的map
     * @return 供货商集合
     */
    Integer queryVendorsTotalCount(Map<String,Object> map);

    /**
     * 根据供应商编号查询一个供应商及其相关信息
     * @param vendorCode 供应商编号
     * @return 一个供应商
     */
    Vendor queryVendorByVendorCode(@Param("vendorCode") String vendorCode);

    /**
     * 增加一个供应商
     * @param vendor 供应商
     */
    void addVendor(Vendor vendor);

    /**
     * 判断这个供货商的编码是否存在
     * @param vendorCode 供货商编码
     * @return 返回1表示存在，0表示不存在
     */
    Integer isVendorExistByVendorCode(@Param("vendorCode") String vendorCode);

    /**
     * 更新一个用户
     * @param vendor 需要更新的用户信息
     */
    void updateVendor(Vendor vendor);

    /**
     * 给供应商增加供应的商品
     * @param commoditiesCode 供应商品编号的集合
     * @param vendorCode  供应商编号
     */
    void addVendorWithCommodities(@Param("commoditiesCode") List<String> commoditiesCode,
                                  @Param("vendorCode") String vendorCode);

    /**
     * 在供货商中删除一个商品
     * @param commodityCode 商品编码
     * @param vendorCode 供货商编码
     */
    void deleteVendorCommodity(@Param("commodityCode") String commodityCode,
                               @Param("vendorCode") String vendorCode);

    /**
     * 查询所有的供货商
     * @return 返回所有供货商的集合
     */
    List<Vendor> queryAllVendors();

    Integer queryAllCounts();
}
