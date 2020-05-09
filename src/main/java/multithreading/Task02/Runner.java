package multithreading.Task02;

import java.util.function.IntConsumer;

public class Runner {
    static boolean isNeedToCallThreadA = false;
    static boolean isNeedToCallThreadB;
    static boolean isNeedToCallThreadC;
    static boolean isNeedToCallThreadD;
    static Thread threadA;
    static Thread threadB;
    static Thread threadC;
    static Thread threadD;

    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Runnable fizzRunnable = () -> System.out.print("fizz ");
        Runnable buzzRunnable = () -> System.out.print("buzz ");
        Runnable fizzBuzzRunnable = () -> System.out.print("fizzbuzz ");
        IntConsumer numberConsumer = n -> System.out.print(n + " ");
        threadA = new Thread(new FizzRunnable(fizzBuzz, fizzRunnable));
        threadB = new Thread(new BuzzRunnable(fizzBuzz, buzzRunnable));
        threadC = new Thread(new FizzBuzzRunnable(fizzBuzz, fizzBuzzRunnable));
        threadD = new Thread(new PrintNumberRunnable(fizzBuzz, numberConsumer));
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        synchronized (Runner.class) {
            new Runner().executingTasks(fizzBuzz);
        }
    }

    private void executingTasks(FizzBuzz fizzBuzz) throws InterruptedException {
        for (int i = 1; i <= fizzBuzz.getN(); i++) {
            if (i % 15 == 0) {
                threadC.interrupt();
                isNeedToCallThreadC = true;
                Thread.sleep(1000);
//                    monitorC.notify();
            } else if (i % 3 == 0) {
                threadA.interrupt();
                isNeedToCallThreadA = true;
                Thread.sleep(1000);
//                    monitorA.notify();
            } else if (i % 5 == 0) {
                threadB.interrupt();
                isNeedToCallThreadB = true;
                Thread.sleep(1000);
//                    monitorB.notify();
            } else {
                threadD.interrupt();
//                isNeedToCallThreadD = true;
                Thread.sleep(1000);
//                    monitorD.notify();
            }
        }
    }
}

class FizzRunnable implements Runnable {
    FizzBuzz fizzBuzz;
    Runnable fizzRunnable;

    public FizzRunnable(FizzBuzz fizzBuzz, Runnable fizzRunnable) {
        this.fizzBuzz = fizzBuzz;
        this.fizzRunnable = fizzRunnable;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
//                Runner.isNeedToCallThreadD = false;
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.fizz(fizzRunnable);
                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
                }
//                e.printStackTrace();
            }
        }
    }
}

class BuzzRunnable implements Runnable {
    FizzBuzz fizzBuzz;
    Runnable buzzRunnable;

    public BuzzRunnable(FizzBuzz fizzBuzz, Runnable buzzRunnable) {
        this.fizzBuzz = fizzBuzz;
        this.buzzRunnable = buzzRunnable;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
//                Runner.isNeedToCallThreadD = false;
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.fizz(buzzRunnable);
                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
                }
//                e.printStackTrace();
            }
        }
    }
}

class FizzBuzzRunnable implements Runnable {
    FizzBuzz fizzBuzz;
    Runnable fizzBuzzRunnable;

    public FizzBuzzRunnable(FizzBuzz fizzBuzz, Runnable fizzBuzzRunnable) {
        this.fizzBuzz = fizzBuzz;
        this.fizzBuzzRunnable = fizzBuzzRunnable;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
//                Runner.isNeedToCallThreadD = false;
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.fizz(fizzBuzzRunnable);
                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
                }
//                e.printStackTrace();
            }
        }
    }
}

class PrintNumberRunnable implements Runnable {
    FizzBuzz fizzBuzz;
    IntConsumer consumer;

    public PrintNumberRunnable(FizzBuzz fizzBuzz, IntConsumer consumer) {
        this.fizzBuzz = fizzBuzz;
        this.consumer = consumer;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                Runner.isNeedToCallThreadD = false;
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.number(consumer);
                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
                }
//                e.printStackTrace();
            }
        }
    }
}