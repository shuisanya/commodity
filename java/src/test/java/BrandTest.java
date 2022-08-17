import com.mhy.commodity.pojo.Brand;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.service.BrandService;
import com.mhy.commodity.service.BrandServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class BrandTest {
    ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");

    BrandService brandService = context.getBean("brandServiceImpl", BrandServiceImpl.class);

    @Test
    public void queryBrands(){
        Map<String,Object> map = new HashMap<>();

        map.put("currentPage",1);
        map.put("pageSize",4);

        Page<Brand> brandPage = brandService.queryBrands(map);

        System.out.println(brandPage);
    }
}
