package multithreading.Task01;


public class Runner {
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        Thread threadA = new Thread(new FirstRunnable(foo));
        Thread threadB = new Thread(new SecondRunnable(foo));
        Thread threadC = new Thread(new ThirdRunnable(foo));
        threadA.start();
        threadA.join();
        threadB.start();
        threadB.join();
        threadC.start();
    }
}

class FirstRunnable implements Runnable {
    Foo foo;

    public FirstRunnable(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        foo.first();
    }
}

class SecondRunnable implements Runnable {
    Foo foo;

    public SecondRunnable(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        foo.second();
    }
}

class ThirdRunnable implements Runnable {
    Foo foo;

    public ThirdRunnable(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        foo.third();
    }
}
