package multithreading.Task01;


public class Foo {
    public volatile int a;

    public void first() {
        a = 1;
        System.out.print("first");
    }

    public void second() {
        a = 2;
        System.out.print("second");
    }

    public void third() {
        a = 3;
        System.out.print("third");
    }
}
