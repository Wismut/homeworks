package multithreading.Task02;

import java.util.function.IntConsumer;

public class Runner {
    static int i;

    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Runnable fizzRunnable = () -> System.out.print("fizz ");
        Runnable buzzRunnable = () -> System.out.print("buzz ");
        Runnable fizzBuzzRunnable = () -> System.out.print("fizzbuzz ");
        IntConsumer numberConsumer = n -> System.out.print(n + " ");
        Thread threadA = new Thread(new FizzRunnable(fizzBuzz, fizzRunnable));
        Thread threadB = new Thread(new BuzzRunnable(fizzBuzz, buzzRunnable));
        Thread threadC = new Thread(new FizzBuzzRunnable(fizzBuzz, fizzBuzzRunnable));
        Thread threadD = new Thread(new PrintNumberRunnable(fizzBuzz, numberConsumer));
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        executingTasks(fizzBuzz, threadA, threadB, threadC, threadD);
    }

    private static void executingTasks(FizzBuzz fizzBuzz,
                                       Thread threadA,
                                       Thread threadB,
                                       Thread threadC,
                                       Thread threadD) throws InterruptedException {
        for (i = 1; i <= fizzBuzz.getN(); i++) {
            if (i % 15 == 0) {
                threadC.interrupt();
                Thread.sleep(200);
            } else if (i % 3 == 0) {
                threadA.interrupt();
                Thread.sleep(200);
            } else if (i % 5 == 0) {
                threadB.interrupt();
                Thread.sleep(200);
            } else {
                threadD.interrupt();
                Thread.sleep(200);
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
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.fizz(fizzRunnable);
                } catch (InterruptedException e1) {

                }
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
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.buzz(buzzRunnable);
                } catch (InterruptedException e1) {

                }
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
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.fizzbuzz(fizzBuzzRunnable);
                } catch (InterruptedException e1) {

                }
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
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.number(consumer);
                } catch (InterruptedException e1) {

                }
            }
        }
    }
}
