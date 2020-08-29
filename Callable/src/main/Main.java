package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		List<Future> allFutures = new ArrayList<Future>();
		for ( int i = 0; i<100; i++) {
			Future<Integer> future = service.submit(new Task());
			allFutures.add(future);
		}
	    
		int result = 0;
		for(int i = 0; i<allFutures.size();i++) {
			Future<Integer> future =  allFutures.get(i);
			result = result+future.get();
		}
	    
		System.out.println(result);
	    
	    System.out.println(Thread.currentThread().getName());
	    
		
	}

}

class Task implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		Thread.sleep(10);
		System.out.println(Thread.currentThread().getName());
		return new Random().nextInt(100);
	}
	
	
	
}
