import com.mhy.commodity.pojo.Commodity;
import com.mhy.commodity.service.CommodityService;
import com.mhy.commodity.service.CommodityServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CommodityTest {
    ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");

    CommodityService commodityService = context.getBean("commodityServiceImpl", CommodityServiceImpl.class);


    @Test
    public void queryCommodityByCommodityCode(){
        Commodity bill2202_001 = commodityService.queryCommodityByCommodityCode("BILL2202_001");

        System.out.println(bill2202_001);
    }
}
