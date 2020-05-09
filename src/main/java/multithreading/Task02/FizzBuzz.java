package multithreading.Task02;

import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        printFizz.run();
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        printBuzz.run();
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        printFizzBuzz.run();
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        printNumber.accept(Runner.i);
    }

    public int getN() {
        return n;
    }
}
