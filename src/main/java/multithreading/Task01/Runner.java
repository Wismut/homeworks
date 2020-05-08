package multithreading.Task01;


public class Runner {
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        Thread threadA = new Thread(foo::first);
        Thread threadB = new Thread(foo::second);
        Thread threadC = new Thread(foo::third);
        threadA.start();
        threadA.join();
        threadB.start();
        threadB.join();
        threadC.start();
    }
}
