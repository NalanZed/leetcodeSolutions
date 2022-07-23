/**
 * @Author: jkun
 * @Description:A对象不论是在栈中还是直接new而无引用,其内部的静态对象都不会被GC
 * @Date: Create in 10:04 2021/6/21
 */
public class GCRootsTest {

    public static void createA(){
        A a = new A();
    }

    public static void main(String[] args) throws InterruptedException {
//        createA();
        new A();
//        A.setB(null);
        //  显式出发full GC
        System.gc();
        System.out.println("^^^^GC");
        System.out.println(A.getB());
        System.out.println(A.getB().getName());
        System.out.println(A.getC());
        //  强制系统调用finalize()
        System.out.println("强制调用finalize");
        System. runFinalization();
        int count = 0;
        while (count<10) {
            Thread.sleep(1000);
            System.out.println("睡眠"+count++);
        }

    }

    public void test () throws InterruptedException {

    }

}




class A{
    private static B b = new B();   //  用了static就没被回收,没用就回收掉了
    private static int c = 10;   //  用了static就没被回收,没用就回收掉了

    public A() {
        System.out.println("A被构造了");
    }

    public static int getC() {
        return c;
    }

    public static void setC(int c) {
        A.c = c;
    }

    public void finalize(){// jdk9之后属过时方法,在对象GC时调用
        System.out.println("A被GC了");
    }

    public static B getB() {
        return b;
    }

    public static void setB(B b) {
        A.b = b;
    }
}

class B{
    @Override
    public String toString() {
        return "B{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private String name = "你好";
    private int  age = 10;

    public void finalize(){
        System.out.println("B被GC了");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


