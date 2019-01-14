//package cc.demo.utils;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SatisfactionModelService {
//
//    public static void main(String[] args) {
//        SatisfactionModelService service = new SatisfactionModelService();
//        Long resultLong = service.safeRun(()->service.throwException());
//        List<Integer> integers = new ArrayList<>();
//        integers.stream().filter(x -> x > 0).mapToInt(x -> x).max().getAsInt();
//        int resultInt = service.safeRun(()->service.getANumber());
//        int resultInt2 = service.safeRun(()->{return  1;});
//        int resultInt3 = service.safeRun(()->{return 2;});
//
//        System.out.println(resultInt);
//        System.out.println(resultInt2);
//        System.out.println(resultInt3);
//        System.out.println(resultLong);
//
//    }
//
//    public int getANumber(){
//        return 1;
//    }
//
//    public Long throwException(){
//        throw new NullPointerException();
//    }
//
//    public <T> T safeRun(IDebug<T> iDebug){
//        try {
//            return iDebug.run();
//        }catch (Exception e){
//            return null;
//        }
//    }
//}
//
//interface IDebug<T>{
//    T run();
//}