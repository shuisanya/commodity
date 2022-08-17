package com.mhy.commodity.service;

import com.mhy.commodity.mapper.AdminMapper;
import com.mhy.commodity.pojo.Admin;
import com.mhy.commodity.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("adminServiceImpl")
public class AdminServiceImpl implements AdminService {
    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Page<Admin> pageQueryAdminList(int currentPage, int pageSize) {
        //创建一个分页的实体类
        Page<Admin> page = new Page<>();

        //查询管理员总的数量
        Integer pageTotalCount = adminMapper.queryAdminsTotalCount(null,null);
        //设置管理员的总的数量
        page.setPageTotalCount(pageTotalCount);

        //设置每页的数量
        page.setPageSize(pageSize);

        //求出总的页数
        int pageTotal = page.getPageTotalCount() / page.getPageSize();
        //处理整数问题 如果余数大于0 页数就加1
        if(page.getPageTotalCount() % page.getPageSize() > 0 ){
            pageTotal += 1;
        }
        //设置总的页数
        page.setPageTotal(pageTotal);

        page.setCurrentPage(currentPage);
        //获取开始位置
        int begin = (page.getCurrentPage() - 1) * page.getPageSize();

        //查询根据页数查询数据
        List<Admin> admins = adminMapper.queryAdminList(begin, page.getPageSize());

        //设置获取的数据
        page.setItems(admins);

        return page;
    }

    @Override
    public Page<Admin> pageSearchAdminList(Map<String, Object> searchMap) {
        Integer pageSize = (Integer) searchMap.get("pageSize");
        Integer currentPage = (Integer) searchMap.get("currentPage");
        //创建一个分页的实体类
        Page<Admin> page = new Page<>();
        //搜索查询管理员总的数量
        Integer pageTotalCount = adminMapper.queryAdminsTotalCount((String)searchMap.get("searchAdminName"),(String)searchMap.get("searchAdminRole"));
        //设置管理员的总的数量
        page.setPageTotalCount(pageTotalCount);

        //设置每页的数量
        page.setPageSize(pageSize);

        //求出总的页数
        int pageTotal = page.getPageTotalCount() / page.getPageSize();
        //处理整数问题 如果余数大于0 页数就加1
        if(page.getPageTotalCount() % page.getPageSize() > 0 ){
            pageTotal += 1;
        }
        //设置总的页数
        page.setPageTotal(pageTotal);

        page.setCurrentPage(currentPage);
        //获取开始位置
        int begin = (page.getCurrentPage() - 1) * page.getPageSize();

         Map<String,Object> map = new HashMap<>();
         map = searchMap;
         map.put("begin",begin);
//        System.out.println(map);
        //查询根据页数查询数据
        List<Admin> admins = adminMapper.searchAdminList(map);

        //设置获取的数据
        page.setItems(admins);

        return page;
    }

    @Override
    public boolean isAdminCodeExist(String adminCode) {
//        获取查询的数据
        String queryAdminCode = adminMapper.queryAdminCodeExist(adminCode);
//        判断是否存在
        return "".equals(queryAdminCode) || queryAdminCode != null;
    }

    @Override
    public void addAdmin(Admin admin) {
        adminMapper.addAdmin(admin);
    }

    @Override
    public void updateAdminById(Admin admin) {
        adminMapper.updateAdminById(admin);
    }

    @Override
    public void deleteAdminById(Integer id) {
        adminMapper.deleteAdminById(id);
    }

    @Override
    public Admin login(Map<String, Object> map) {
        return adminMapper.login(map);
    }

}
