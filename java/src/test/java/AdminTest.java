import com.alibaba.fastjson.JSON;
import com.mhy.commodity.pojo.Admin;
import com.mhy.commodity.pojo.Page;
import com.mhy.commodity.service.AdminServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.java2d.loops.DrawGlyphList;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdminTest  {

    ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");

    AdminServiceImpl adminServiceImpl = context.getBean("adminServiceImpl", AdminServiceImpl.class);

    @Test
    public void pageQueryAdminList(){

        Page<Admin> page = adminServiceImpl.pageQueryAdminList(1, 5);

//        Date date = page.getItems().get(0).getBirthday();
        System.out.println(page);
        System.out.println(JSON.toJSONString(page));
    }

    @Test
    public void pageSearchAdminList(){

        Map<String,Object> map = new HashMap<>();
        map.put("pageSize",2);
        map.put("currentPage",1);
        map.put("searchAdminRole","2");
        map.put("searchAdminName","");

        Page<Admin> page = adminServiceImpl.pageSearchAdminList(map);

        System.out.println(page);
        System.out.println(JSON.toJSONString(page));
    }

    @Test
    public void isAdminCodeExist(){
        boolean isExist = adminServiceImpl.isAdminCodeExist("hy123");

        System.out.println(isExist);
    }

    @Test
    public void addAdmin(){
        Admin admin = new Admin();
        admin.setAdminCode("mhy999");
        admin.setAdminPassword("123456");
        admin.setAdminName("哈哈哈");
        admin.setSex(1);
        admin.setBirthday(new Date());
        admin.setPhone("13878789696");
        admin.setAdminRole("3");
        admin.setAddress("重庆交通大学");

        adminServiceImpl.addAdmin(admin);

    }

    @Test
    public void updateAdminById(){
        Admin admin = new Admin();
        admin.setAdminCode("mhy999");
        admin.setAdminPassword("111111");
        admin.setAdminName("王大眼");
        admin.setSex(1);
        admin.setBirthday(new Date());
        admin.setPhone("13878789696");
        admin.setAdminRole("3");
        admin.setAddress("重庆交通大学德园");
        admin.setId(15);

        adminServiceImpl.updateAdminById(admin);

    }

    @Test
    public void deleteAdminById(){


        adminServiceImpl.deleteAdminById(16);

    }
    
    
    
    @Test
    public void test() throws UnknownHostException {

        //获取本机的ip地址
        System.out.println(InetAddress.getLocalHost().getHostAddress());

        int n = 8;
        for(int i =0;i<n;i++){
            for(int j = 0;j<i;j++){
                System.out.print(" ");
            }
            for(int k = 0;k<n-i;k++){
                System.out.print("*");
            }
            System.out.println();
        }

    }



}
