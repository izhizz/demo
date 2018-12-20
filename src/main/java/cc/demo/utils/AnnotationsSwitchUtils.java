package cc.demo.utils;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 只能用在Controller里面
 */
public class AnnotationsSwitchUtils {
    public static final HashMap<String, Integer> controllerPathSwichMap = new HashMap<>();
    public static final Integer UP = 1;
    public static final Integer DOWN = 0;


    //    判断是否打印
    public static void sout(String message) {
        String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名

        String method_name = new Exception().getStackTrace()[1].getMethodName();//获取调用方法名

        String methodPath = getProjectPath(classname, method_name);
        if (methodPath == null) {
            return;
        }
        if (null == controllerPathSwichMap.get(method_name)) {
            controllerPathSwichMap.put(methodPath, UP);
            System.out.println(classname + "类" + "---->" + method_name + "方法:打印日志如下");
            System.out.println(message);
        } else {
            if (UP.equals(controllerPathSwichMap.get(methodPath))) {
                System.out.println(classname + "类" + "---->" + method_name + "方法:打印日志如下");
                System.out.println(message);
            }
        }

    }

    //    获取调用路径
    private static String getProjectPath(String _className, String _methodName) {
        Class<?> clzz = null;
        Method declaredMethod = null;
        try {
            clzz = Class.forName(_className);
            declaredMethod = clzz.getDeclaredMethod(_methodName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }

        RequestMapping className = clzz.getAnnotation(RequestMapping.class);
        RequestMapping methodName = declaredMethod.getAnnotation(RequestMapping.class);

        String classPath = "";
        String methodPath = "";
        for (String path : className.value()) {
            classPath = path;
        }
        for (String path : methodName.value()) {
            methodPath = path;
        }
        return classPath + methodPath;
    }


    //    设置是否显示打印信息
    public static Integer setSiwtch(String path, Integer switchValue) {
        if (UP.equals(switchValue)) {
            controllerPathSwichMap.put(path, switchValue);
            return 1;
        } else if (DOWN.equals(switchValue)) {
            controllerPathSwichMap.put(path, switchValue);
            return 1;
        }
        return 0;
    }
}
