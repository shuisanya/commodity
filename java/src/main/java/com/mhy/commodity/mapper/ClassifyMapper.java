package com.mhy.commodity.mapper;

import com.mhy.commodity.pojo.Classify;

import java.util.List;

public interface ClassifyMapper {

    /**
     * 查询所有的分类
     * @return 分类的集合
     */
    List<Classify> queryAllClassifies();

    Integer queryAllCounts();

}
