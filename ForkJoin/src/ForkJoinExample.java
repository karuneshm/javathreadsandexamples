
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

	public static void main(String[] args) {
		Fibonacci fibonacci = new Fibonacci(5);
	
		System.out.println(fibonacci.compute());
		
		Factorial fact = new Factorial(5);
		
		System.out.println(fact.compute());
		

	}

}




class Fibonacci extends RecursiveTask<Integer> {
	
	private int number;

	public Fibonacci(int number) {
		this.number = number;
	}



	@Override
	protected Integer compute() {
		if(number<=1)
		{
			return 1;
		} else {
			Fibonacci fib1 = new Fibonacci(number-1);
			fib1.fork();
			Fibonacci fib2 = new Fibonacci(number-2);
			fib2.fork();
			return fib1.join()+fib2.join();
		}
	}
	
}


class Factorial extends RecursiveTask<Integer> {
	
	private int number;

	public Factorial(int number) {
		this.number = number;
	}



	@Override
	protected Integer compute() {
		if(number == 0 || number ==1)
		{
			return 1;
		} else {
			Factorial fact1 = new Factorial(number-1);
			fact1.fork();
			Factorial fact2 = new Factorial(number-2);
			fact2.fork();
			return fact1.join()*fact2.join();
		}
	}
	
}