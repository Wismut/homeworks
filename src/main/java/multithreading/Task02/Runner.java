package multithreading.Task02;

import java.util.function.IntConsumer;

public class Runner {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Runnable fizzRunnable = () -> System.out.print("fizz ");
        Runnable buzzRunnable = () -> System.out.print("buzz ");
        Runnable fizzBuzzRunnable = () -> System.out.print("fizzbuzz ");
        IntConsumer numberConsumer = n -> System.out.print(n + " ");;
        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz(fizzRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz(buzzRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(fizzBuzzRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadD = new Thread(new PrintNumberRunnable(fizzBuzz, numberConsumer));
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
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
    public void run() {
        try {
            fizzBuzz.number(consumer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}