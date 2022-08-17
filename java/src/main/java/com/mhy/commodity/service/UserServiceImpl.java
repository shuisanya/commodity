package com.mhy.commodity.service;

import com.mhy.commodity.mapper.OrderMapper;
import com.mhy.commodity.mapper.UserMapper;
import com.mhy.commodity.pojo.Order;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private OrderMapper orderMapper;

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public Page<User> queryUserList(Map<String, Object> map) {

        Page<User> userPage = new Page<>();

        //获取当前页数和每页数量
        int pageSize = (Integer) map.get("pageSize");
        int currentPage = (Integer) map.get("currentPage");

        //按要求查询用户数量
        int pageTotalCount = userMapper.queryUsersTotalCount((String)map.get("searchUserName"));
        //设置管理员的总的数量
        userPage.setPageTotalCount(pageTotalCount);

        //设置每页的数量
        userPage.setPageSize(pageSize);

        //求出总的页数
        int pageTotal = userPage.getPageTotalCount() / userPage.getPageSize();
        //处理整数问题 如果余数大于0 页数就加1
        if(userPage.getPageTotalCount() % userPage.getPageSize() > 0 ){
            pageTotal += 1;
        }
        //设置总的页数
        userPage.setPageTotal(pageTotal);

        userPage.setCurrentPage(currentPage);
        //获取开始位置
        int begin = (userPage.getCurrentPage() - 1) * userPage.getPageSize();
        map.put("begin",begin);

        //查询User
        List<User> users = userMapper.queryUserList(map);

        //遍历查询的用户信息，获取订单信息
        for (User user : users) {
            String userCode = user.getUserCode();
            if (!"".equals(userCode)) {
                List<Order> orders = orderMapper.queryAllOrderByUserCode(userCode);
                user.setOrders(orders);
            }
        }
        userPage.setItems(users);

        return userPage;
    }
}
