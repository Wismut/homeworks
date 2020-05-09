package multithreading.Task01;


public class Foo {
    volatile boolean isFirstCalled, isSecondCalled;

    public void first() {
        System.out.print("first");
        isFirstCalled = true;
    }

    public void second() {
        while (!isFirstCalled) ;
        System.out.print("second");
        isSecondCalled = true;
    }

    public void third() {
        while (!isSecondCalled) ;
        System.out.print("third");
    }
}
