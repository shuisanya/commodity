import com.mhy.commodity.pojo.Vendor;
import com.mhy.commodity.service.VendorService;
import com.mhy.commodity.service.VendorServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendorTest {
    ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");

    VendorService vendorService = context.getBean("vendorServiceImpl", VendorServiceImpl.class);


    @Test
    public void queryVendors(){
        Map<String,Object> map = new HashMap<>();

        map.put("currentPage",1);
        map.put("pageSize",4);

        List<Vendor> vendors = vendorService.queryVendors(map).getItems();

        for (Vendor vendor : vendors) {
            System.out.println(vendor);
        }
    }    @Test
    public void queryVendorByVendorCode(){

        Vendor bj_gys001 = vendorService.queryVendorByVendorCode("BJ_GYS001");

        System.out.println(bj_gys001);
    }

}
