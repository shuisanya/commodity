package com.mhy.commodity.pojo;

import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Page是分页的模型对象
 * @param <T> 是具体的模块的JavaBean类
 */
@Component("page")
public class Page<T> {

    public static final Integer PAGE_SIZE = 5;

    //当前页码
    private Integer currentPage;
    //总页码
    private Integer pageTotal;
    //当前页面显示的数据的量
    private Integer pageSize = PAGE_SIZE;
    //总记录数
    private  Integer pageTotalCount;
    //当前页面数据
    private List<T> items;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        //设置分页边界
        if(currentPage < 1){
            currentPage = 1;
        }
        if(currentPage > pageTotal){
            currentPage = pageTotal;
        }

        this.currentPage = currentPage;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + currentPage +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                '}';
    }
}
