package cc.GetClassNameAndMethodName.utils;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 方法中的任何含有输出方法
 */
public class ClassNameSwitchUtils {
    public static final HashMap<String, Map<String, Integer>> controllerPathSwichMap = new HashMap<>();
    public static final Integer UP = 1;
    public static final Integer DOWN = 0;


    //    判断是否打印
    public static void sout(String message) {
        String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名

        String method_name = new Exception().getStackTrace()[1].getMethodName();//获取调用方法名
        Integer lines = new Exception().getStackTrace()[1].getLineNumber();//获取调用行数

        if (null == controllerPathSwichMap.get(classname) || null == controllerPathSwichMap.get(classname).get(method_name)) {
            if (controllerPathSwichMap.get(classname) == null) {
                Map<String, Integer> dataMap = new HashMap<>();
                dataMap.put(method_name, UP);
                controllerPathSwichMap.put(classname, dataMap);
            }

            if (null == controllerPathSwichMap.get(classname).get(method_name)) {
                controllerPathSwichMap.get(classname).put(method_name, UP);
            }

            System.out.println(classname + "类" + "---->第" + lines + "行---->" + method_name + "方法:打印日志如下");
            System.out.println(message);
        } else {
            if (UP.equals(controllerPathSwichMap.get(classname).get(method_name))) {
                System.out.println(classname + "类" + "---->第" + lines + "行---->" + method_name + "方法:打印日志如下");
                System.out.println(message);
            }
        }

    }

    //    输出形式以文件格式
    public static void outFileLog(String name, String content) {
//        如果是多数据源的话
//        String dbKey = DataSourceUtils.getDbKey();
//        if (!StringUtils.isEmpty(dbKey)) {
//            dbKey = dbKey + "/";
//        } else {
//            dbKey = "";
//        }
        String path = "";
//        String time = ConstantUtil.longToDate(ConstantUtil.getTimesZeroStamp(), "yyyy-MM-dd");
//        if (getOfficeHome().contains("Windows")) {
//            path = "E:/file/" + dbKey + time + "/";
//        } else if (getOfficeHome().contains("Windows")) {
//            path = "/opt/file/" + dbKey + time + "/";
//        }
        if (getOfficeHome().contains("Windows")) {
            path = "E:/file/";
        } else if (getOfficeHome().contains("Windows")) {
            path = "/opt/file/";
        }
        BufferedWriter out = null;
        try {

            File file = new File(path + name + ".text");
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path + name + ".text", true)));
            out.write(content + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //初始化所有的打印日志信息
    public static void initialize() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        PackageUtil.scan("com.wulianedu", list);//把这个对象的路径写入，就可以了。
//        scan("src.main.java.cc.demo",list);//把这个对象的路径写入，就可以了。
        System.out.println(list.size());
        for (Class<?> cla : list) {
            String className = cla.getName();
//            不调用比较器中的二进制文件
            if (className.contains("$")) {
                continue;
            }
            Map<String, Integer> classMap = new HashMap<>();
            Method[] methods = cla.getDeclaredMethods();
            for (Method method : methods) {
//                打开私有权限
                method.setAccessible(true);
//                屏蔽掉默认方法
                if ("getClass".equals(method.getName())) {
                    continue;
                } else if ("wait".equals(method.getName())) {
                    continue;
                }
                if ("hashCode".equals(method.getName())) {
                    continue;
                }
                if ("equals".equals(method.getName())) {
                    continue;
                }
                if ("notifyAll".equals(method.getName())) {
                    continue;
                }
                if ("toString".equals(method.getName())) {
                    continue;
                }
                if ("notify".equals(method.getName())) {
                    continue;
                }
                classMap.put(method.getName(), 1);
            }
            ClassNameSwitchUtils.controllerPathSwichMap.put(className, classMap);
        }
    }

    //    设置开关日志信息
    public static Integer setSwitch(String className, String menthod, Integer switchValue) {
        if (UP.equals(switchValue)) {
            controllerPathSwichMap.get(className).put(menthod, switchValue);
            return 1;
        } else if (DOWN.equals(switchValue)) {
            controllerPathSwichMap.get(className).put(menthod, switchValue);
            return 1;
        }
        return 0;
    }

    //    一键设置开关日志信息
    public static Integer setAllSwitch(String className, String menthod, Integer switchValue) {
        if (UP.equals(switchValue)) {
            setSwitch(UP);
            return 1;
        } else if (DOWN.equals(switchValue)) {
            setSwitch(DOWN);
            return 1;
        }
        return 0;
    }

    //    批量设置key值
    private static void setSwitch(Integer switchaa) {
        for (Map.Entry<String, Map<String, Integer>> entry : controllerPathSwichMap.entrySet()) {
            for (Map.Entry<String, Integer> data : entry.getValue().entrySet()) {
                data.setValue(switchaa);
            }
        }
    }


    private static String getOfficeHome() {

        String osName = System.getProperty("os.name");//获取指定键（即os.name）的系统属性,如：Windows 7。
        String OSname = null;
        if (Pattern.matches("Linux.*", osName)) {
            OSname = "Linux";
        } else if (Pattern.matches("Windows.*", osName)) {
            OSname = "Windows";
        } else if (Pattern.matches("Mac.*", osName)) {
            OSname = "Mac";
        }
        return OSname;

    }

}
