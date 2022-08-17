import com.mhy.commodity.pojo.Order;
import com.mhy.commodity.pojo.OrderDetails;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.service.OrderServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderTest {

    public ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    OrderServiceImpl orderService = context.getBean("orderServiceImpl", OrderServiceImpl.class);


    @Test
    public void queryOrders(){
        Map<String,Object> map = new HashMap<>();

        map.put("currentPage",1);
        map.put("pageSize",5);
//        BigDecimal minPrice = BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP);
//        BigDecimal maxPrice = BigDecimal.valueOf(1000).setScale(2, RoundingMode.HALF_UP);
        Date endTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String df = simpleDateFormat.format(endTime);
        map.put("startTime","2022-03-6 11:43:23");
        map.put("endTime",df);

        Page<Order> orderPage = orderService.queryOrders(map);

        System.out.println(orderPage);

        for (Order item : orderPage.getItems()) {
            System.out.println(item);
        }
    }

    @Test
    public void queryOrderDetailsByOrderCode(){
        Order order = orderService.queryOrderDetailsByOrderCode("16464760757381");

        for (OrderDetails orderDetail : order.getOrderDetails()) {
            System.out.println(orderDetail);
        }
    }

    @Test
    public void shippingOrderDetail(){
        orderService.shippingOrderDetail("SSY_16465441297157_002");
    }

    @Test
    public void test(){
        BigDecimal a = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_UP);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String df = simpleDateFormat.format(date);
        System.out.println(df);
    }


}
