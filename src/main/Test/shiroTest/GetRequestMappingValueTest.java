package shiroTest;


import cc.demo.controller.test;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * 文档描述:通过反射得到requestMapping的value值
 * 作者:赵鹏
 * 时间:2016-10-8 上午09:04:53
 */
public class GetRequestMappingValueTest {


    public static void main(String[] args) throws NoSuchMethodException {
        Package[] packages = Package.getPackages();
        Package packagex = Package.getPackage("aaa");
        Package packagexy = Package.getPackage("shiroTest");
        get();
//1、获取调用的方法名称和方法雷鸣
        String classname = new Exception().getStackTrace()[0].getClassName(); //获取调用者的类名

        String method_name = new Exception().getStackTrace()[0].getMethodName(); //获取调用者的方法名


        Class<?> clazz = test.class;
        Method declaredMethod = clazz.getDeclaredMethod(method_name);
        //得到字节码文件  【只需要更改controller类名】


        //得到方法
        Method[] methods = clazz.getDeclaredMethods();
        boolean annotationPresent = clazz.isAnnotationPresent(RequestMapping.class);
        if (annotationPresent){
            RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);

            //输出 annotation RequestMapping包含的信息(headers=[], name=, path=[], value=[toTicket], produces=[], method=[], params=[], consumes=[])
            //System.err.println(annotation);

            //得到value数组
            String[] value = annotation.value();

            for (String string2 : value) {

                //输出value值
                System.out.println(string2);

            }
        }

        for (Method method : methods) {

            //判断是否存在requestMapping注释
            boolean present = method.isAnnotationPresent(RequestMapping.class);

            if(present){

                //得到requestMapping注释
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);

                //输出 annotation RequestMapping包含的信息(headers=[], name=, path=[], value=[toTicket], produces=[], method=[], params=[], consumes=[])
                //System.err.println(annotation);

                //得到value数组
                String[] value = annotation.value();

                for (String string2 : value) {

                    //输出value值
                    System.out.println(string2);

                }

            }

        }

    }
    public static void get(){
        String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名

        String method_name = new Exception().getStackTrace()[1].getMethodName();
        System.out.println(classname);
        System.out.println(method_name);
    }

}