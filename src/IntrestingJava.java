import java.util.ArrayList;
import java.util.List;

public class IntrestingJava {

    class Man{
        String name;
        int age;
    };

    public void testEquals(){
//        String s1 = "hi";
//        String s1 = new String("hi");
//        String s2 = new String("hi");
//        System.out.println((s1 == s2));
//        System.out.println(s1.equals(s2));
//        String s2 = "hi";
//        final String s4 = "hi";
//        StringBuilder sb = new StringBuilder("hi");
//        String s3 = sb.toString();
//        String s1 = "hi";

        String s1 = "h";
        String s2 = "i";
        String s3 = "hi";
        String s4 = "h"+"i";
        String s5 = s1+"i";
        System.out.println((s4 == "hi"));
        System.out.println((s4 == s3));
        System.out.println((s5 == "hi"));
        System.out.println((s5 == s4));
//        System.out.println(s5.equals("hi"));

        List<String> as = new ArrayList<>();


    }


    public static void main(String[] args) {
        IntrestingJava p = new IntrestingJava();
        p.testEquals();
    }
}
