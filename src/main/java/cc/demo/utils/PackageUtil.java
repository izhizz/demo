package demo.utils;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageUtil {
    public static void scan(String packageName ,List<Class<?>> list) throws Exception{

        String path=getSrcPath()+packageToDir(packageName);
        File dir=new File(path);
        File[] files=dir.listFiles();
        Class<?> cla=null;
        for(File f:files){
            if(f.isDirectory()){
                if (packageName.contains("persistence")){
                    continue;
                }
                String childName=packageName+"."+f.getName();
                scan(childName, list);

            }else{
                try {
                    cla=Class.forName(packageName+"."+f.getName().split("\\.")[0]);
                    list.add(cla);
                }catch (Exception e){

                }

            }
        }
    }
    /**
     获取当前路径
     */
    public static String getSrcPath() throws IOException{
        String path = PackageUtil.class.getClassLoader().getResource("").getPath().substring(1);

//        String path1 = (String) classLoader
//        File file=new File("");
//        System.out.println(path1);
//        String path=file.getCanonicalPath()+File.separator+"src\\main\\java";

        return path;
    }
    /**
     *package转换为路径格式
     */
    public static String packageToDir(String packageName){
        if (StringUtils.isEmpty(packageName)){
            return "";
        }
        String[] array=packageName.split("\\.");
        StringBuffer sb=new StringBuffer();
        for(String str:array){
            sb.append("/").append(str);
        }
        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        List<Class<?>> list=new ArrayList<Class<?>>();
        scan("demo",list);//把这个对象的路径写入，就可以了。
//        scan("src.main.java.demo",list);//把这个对象的路径写入，就可以了。
        System.out.println(list.size());
        for(Class<?> cla:list){
            String className = cla.getName();
            if (className.contains("$")){
                continue;
            }
            Map<String,Integer> classMap = new HashMap<>();
            Method[] methods = cla.getMethods();
            for (Method  method : methods){
                if("getClass".equals(method.getName())){
                 continue;
                }else if("wait".equals(method.getName())){
                    continue;
                }if("hashCode".equals(method.getName())){
                    continue;
                }if("equals".equals(method.getName())){
                    continue;
                }if("notifyAll".equals(method.getName())){
                    continue;
                }if("toString".equals(method.getName())){
                    continue;
                }if("notify".equals(method.getName())){
                    continue;
                }
                classMap.put(method.getName(),1);
            }
            ClassNameSwitchUtils.controllerPathSwichMap.put(className,classMap);


        }
        for (Map.Entry<String,Map<String,Integer>> entry : ClassNameSwitchUtils.controllerPathSwichMap.entrySet()){

            System.out.println( entry.getKey());

            for(Map.Entry<String,Integer> data : entry.getValue().entrySet()){
                System.out.print(data.getKey());
                System.out.print("========");
                System.out.println(data.getValue());
            }
        }

    }
}