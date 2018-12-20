package shiroTest;

import org.junit.Test;

public class AssertTest {

    public static void main(String[] args) {
        String name = "123";
        assert ("1234".equals(name)):789;
        System.out.println(name);
    }
    @Test
    public void aaa(){
        String name = "123";
        assert (!"1234".equals(name)):789;
        System.out.println(name);
    }
}
