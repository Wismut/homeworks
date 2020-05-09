package multithreading.Task02;

import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

public class Runner {
    static int i;

    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Runnable fizzRunnable = () -> System.out.print("fizz ");
        Runnable buzzRunnable = () -> System.out.print("buzz ");
        Runnable fizzBuzzRunnable = () -> System.out.print("fizzbuzz ");
        IntConsumer numberConsumer = n -> System.out.print(n + " ");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread threadA = new Thread(new FizzRunnable(fizzBuzz, fizzRunnable, countDownLatch));
        threadA.setDaemon(true);
        threadA.start();
        Thread threadB = new Thread(new BuzzRunnable(fizzBuzz, buzzRunnable, countDownLatch));
        threadB.setDaemon(true);
        threadB.start();
        Thread threadC = new Thread(new FizzBuzzRunnable(fizzBuzz, fizzBuzzRunnable, countDownLatch));
        threadC.setDaemon(true);
        threadC.start();
        Thread threadD = new Thread(new PrintNumberRunnable(fizzBuzz, numberConsumer, countDownLatch));
        threadD.setDaemon(true);
        threadD.start();
        executingTasks(fizzBuzz, countDownLatch, threadA, threadB, threadC, threadD);
    }

    private static void executingTasks(FizzBuzz fizzBuzz,
                                       CountDownLatch countDownLatch,
                                       Thread threadA,
                                       Thread threadB,
                                       Thread threadC,
                                       Thread threadD) throws InterruptedException {
        for (i = 1; i <= fizzBuzz.getN(); i++) {
            countDownLatch = new CountDownLatch(1);
            if (i % 15 == 0) {
                threadC.interrupt();
                countDownLatch.await();
            } else if (i % 3 == 0) {
                threadA.interrupt();
                countDownLatch.await();
            } else if (i % 5 == 0) {
                threadB.interrupt();
                countDownLatch.await();
            } else {
                threadD.interrupt();
                countDownLatch.await();
            }
        }
    }
}

class FizzRunnable implements Runnable {
    FizzBuzz fizzBuzz;
    Runnable fizzRunnable;
    CountDownLatch countDownLatch;

    public FizzRunnable(FizzBuzz fizzBuzz, Runnable fizzRunnable, CountDownLatch countDownLatch) {
        this.fizzBuzz = fizzBuzz;
        this.fizzRunnable = fizzRunnable;
        this.countDownLatch = countDownLatch;
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

                } finally {
                    countDownLatch.countDown();
                }
            }
        }
    }
}

class BuzzRunnable implements Runnable {
    private final CountDownLatch countDownLatch;
    FizzBuzz fizzBuzz;
    Runnable buzzRunnable;

    public BuzzRunnable(FizzBuzz fizzBuzz, Runnable buzzRunnable, CountDownLatch countDownLatch) {
        this.fizzBuzz = fizzBuzz;
        this.buzzRunnable = buzzRunnable;
        this.countDownLatch = countDownLatch;
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

                } finally {
                    countDownLatch.countDown();
                }
            }
        }
    }
}

class FizzBuzzRunnable implements Runnable {
    private final CountDownLatch countDownLatch;
    FizzBuzz fizzBuzz;
    Runnable fizzBuzzRunnable;

    public FizzBuzzRunnable(FizzBuzz fizzBuzz, Runnable fizzBuzzRunnable, CountDownLatch countDownLatch) {
        this.fizzBuzz = fizzBuzz;
        this.fizzBuzzRunnable = fizzBuzzRunnable;
        this.countDownLatch = countDownLatch;
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

                } finally {
                    countDownLatch.countDown();
                }
            }
        }
    }
}

class PrintNumberRunnable implements Runnable {
    private final CountDownLatch countDownLatch;
    FizzBuzz fizzBuzz;
    IntConsumer consumer;

    public PrintNumberRunnable(FizzBuzz fizzBuzz, IntConsumer consumer, CountDownLatch countDownLatch) {
        this.fizzBuzz = fizzBuzz;
        this.consumer = consumer;
        this.countDownLatch = countDownLatch;
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

                } finally {
                    countDownLatch.countDown();
                }
            }
        }
    }
}
