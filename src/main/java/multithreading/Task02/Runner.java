package multithreading.Task02;

import java.util.function.IntConsumer;

public class Runner {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Runnable fizzRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizz(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable buzzRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.buzz(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable fizzBuzzRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizzbuzz(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        IntConsumer numberConsumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                try {
                    fizzBuzz.number(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
//        Thread threadA = new Thread(() -> {
//
//        });
//        Thread threadB = new Thread(fizzBuzz::buzz);
//        Thread threadC = new Thread(fizzBuzz::fizzBuzz);
//        Thread threadD = new Thread(fizzBuzz::number);
//        threadA.start();
//        threadB.start();
//        threadC.start();
//        threadD.start();
    }
}
