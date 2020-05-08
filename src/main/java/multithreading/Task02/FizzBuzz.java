package multithreading.Task02;

public class FizzBuzz {
    int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() {
        if (this.n % 3 == 0) {
            System.out.print("fizz");
        }
    }

    public void buzz() {
        if (this.n % 5 == 0) {
            System.out.print("buzz");
        }
    }

    public void fizzBuzz() {
        if (this.n % 3 == 0 &&
                this.n % 5 == 0) {
            System.out.print("fizzbuzz");
        }
    }

    public void number() {
        System.out.print(n);
    }
}
