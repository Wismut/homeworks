package multithreading.Task02;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

public class Runner {
    static int i;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Runnable fizzRunnable = () -> System.out.print("fizz ");
        Runnable buzzRunnable = () -> System.out.print("buzz ");
        Runnable fizzBuzzRunnable = () -> System.out.print("fizzbuzz ");
        IntConsumer numberConsumer = n -> System.out.print(n + " ");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread threadA = new Thread(new FizzRunnable(fizzBuzz, fizzRunnable, cyclicBarrier));
        threadA.setDaemon(true);
        threadA.start();
        Thread threadB = new Thread(new BuzzRunnable(fizzBuzz, buzzRunnable, cyclicBarrier));
        threadB.setDaemon(true);
        threadB.start();
        Thread threadC = new Thread(new FizzBuzzRunnable(fizzBuzz, fizzBuzzRunnable, cyclicBarrier));
        threadC.setDaemon(true);
        threadC.start();
        Thread threadD = new Thread(new PrintNumberRunnable(fizzBuzz, numberConsumer, cyclicBarrier));
        threadD.setDaemon(true);
        threadD.start();
        executingTasks(fizzBuzz, cyclicBarrier, threadA, threadB, threadC, threadD);
    }

    private static void executingTasks(FizzBuzz fizzBuzz,
                                       CyclicBarrier cyclicBarrier,
                                       Thread threadA,
                                       Thread threadB,
                                       Thread threadC,
                                       Thread threadD) throws InterruptedException, BrokenBarrierException {
        for (i = 1; i <= fizzBuzz.getN(); i++) {
            if (i % 15 == 0) {
                threadC.interrupt();
                cyclicBarrier.await();
            } else if (i % 3 == 0) {
                threadA.interrupt();
                cyclicBarrier.await();
            } else if (i % 5 == 0) {
                threadB.interrupt();
                cyclicBarrier.await();
            } else {
                threadD.interrupt();
                cyclicBarrier.await();
            }
        }
    }
}

class FizzRunnable implements Runnable {
    FizzBuzz fizzBuzz;
    Runnable fizzRunnable;
    CyclicBarrier cyclicBarrier;

    public FizzRunnable(FizzBuzz fizzBuzz, Runnable fizzRunnable, CyclicBarrier cyclicBarrier) {
        this.fizzBuzz = fizzBuzz;
        this.fizzRunnable = fizzRunnable;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.fizz(fizzRunnable);
                    cyclicBarrier.await();
                } catch (Exception e1) {

                }
            }
        }
    }
}

class BuzzRunnable implements Runnable {
    private final CyclicBarrier cyclicBarrier;
    FizzBuzz fizzBuzz;
    Runnable buzzRunnable;

    public BuzzRunnable(FizzBuzz fizzBuzz, Runnable buzzRunnable, CyclicBarrier cyclicBarrier) {
        this.fizzBuzz = fizzBuzz;
        this.buzzRunnable = buzzRunnable;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.buzz(buzzRunnable);
                    cyclicBarrier.await();
                } catch (Exception e1) {

                }
            }
        }
    }
}

class FizzBuzzRunnable implements Runnable {
    private final CyclicBarrier cyclicBarrier;
    FizzBuzz fizzBuzz;
    Runnable fizzBuzzRunnable;

    public FizzBuzzRunnable(FizzBuzz fizzBuzz, Runnable fizzBuzzRunnable, CyclicBarrier cyclicBarrier) {
        this.fizzBuzz = fizzBuzz;
        this.fizzBuzzRunnable = fizzBuzzRunnable;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.fizzbuzz(fizzBuzzRunnable);
                    cyclicBarrier.await();
                } catch (Exception e1) {

                }
            }
        }
    }
}

class PrintNumberRunnable implements Runnable {
    private final CyclicBarrier cyclicBarrier;
    FizzBuzz fizzBuzz;
    IntConsumer consumer;

    public PrintNumberRunnable(FizzBuzz fizzBuzz, IntConsumer consumer, CyclicBarrier cyclicBarrier) {
        this.fizzBuzz = fizzBuzz;
        this.consumer = consumer;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                try {
                    fizzBuzz.number(consumer);
                    cyclicBarrier.await();
                } catch (Exception e1) {

                }
            }
        }
    }
}
