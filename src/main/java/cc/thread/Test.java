package cc.thread;

public class Test {
    public static void main(String[] args) {
        System.out.println("aaaa");
        new Thread(() -> {
            for(int i=0;i<1000000000;i++)
            {

            }
            /**处理完其他事情，现在来处理消息**/
            System.out.println("haha");
            System.out.println("I hava executed the message by Local");
            /**执行回调**/
            Thread.interrupted();
        }
        ).start();
        System.out.println("bbbb");
    }
}
