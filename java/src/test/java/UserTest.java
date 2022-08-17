import com.mhy.commodity.pojo.Order;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.pojo.User;
import com.mhy.commodity.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTest {

    ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");

    UserServiceImpl userService = context.getBean("userServiceImpl", UserServiceImpl.class);


    @Test
    public void queryUserList(){
        Map<String,Object> map = new HashMap<>();

        map.put("currentPage",1);
        map.put("pageSize",4);
        map.put("searchUserName","");

        Page<User> userPage = userService.queryUserList(map);

        List<User> users = userPage.getItems();

        for (User user : users) {
            System.out.println(user);
        }
        for (Order order : users.get(0).getOrders()) {
            System.out.println(order);
        }

    }

}
