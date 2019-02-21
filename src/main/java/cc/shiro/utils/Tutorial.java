package cc.shiro.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Tutorial {
    private static final transient Logger log = LoggerFactory.getLogger(Tutorial.class);

    public static void main(String[] args) {
//        log.info("My First Apache Shiro Application");
//        System.exit(0);
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:cc.shiro.ini");
//        //2、得到SecurityManager实例 并绑定给SecurityUtils
//        SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);
//        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
//
//        try {
//            //4、登录，即身份验证
//            subject.login(token);
//        } catch (AuthenticationException e) {
//            //5、身份验证失败
//        }
//
//        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
//
//        //6、退出
//        subject.logout();


//            String first = request.getParameter("first");
//            String second = request.getParameter("second");
//            String _max = request.getParameter("max");
//            String third = request.getParameter("third");
            String first = "2DPY";
            String second = "15070300100000000";
            String _max ="999";
            String third = "1";

            Integer max = Integer.parseInt(_max);
            List<String> value = new ArrayList<>();
            for (Integer i = 0; i < max; i++) {
                int length = _max.length();
                int i_length = i.toString().length();
                String zero="";
                for (int j=0;j<length-i_length;j++){
                    zero+=0;
                }

                String aaa = first+"-"+second+zero+i+"-"+third;
                System.out.println(aaa);
            }


    }


}
