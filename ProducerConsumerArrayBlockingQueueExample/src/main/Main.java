package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static final String EOF = "EOF";

	public static void main(String[] args) {

		ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);

		ExecutorService service = Executors.newFixedThreadPool(3);

		MyProducer myProducer = new MyProducer(buffer, "1");
		MyConsumer myConsumer1 = new MyConsumer(buffer, "2");
		MyConsumer myConsumer2 = new MyConsumer(buffer, "3");
		Thread t1 = new Thread(myProducer);
		Thread t2 = new Thread(myConsumer1);
		Thread t3 = new Thread(myConsumer2);

		service.execute(t1);
		service.execute(t2);
		service.execute(t3);

//		
//		Future<String> future = service.submit(new Callable<String>() {
//
//			@Override
//			public String call() throws Exception {
//				System.out.println("this is a callable result");
//				return "this is a callable result";
//			}
//		});
//		
//		
//		
//		try  {
//			System.out.println(future.get());
//		} catch(ExecutionException e){
//			
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		service.shutdown();

	}

}

class MyProducer implements Runnable {
	private ArrayBlockingQueue<String> buffer;
	private String name;

	public MyProducer(ArrayBlockingQueue<String> buffer, String name) {
		this.buffer = buffer;
		this.name = name;
	}

	@Override
	public void run() {
		Random random = new Random();
		String[] nums = { "1", "2", "3", "4", "5" };

		for (String num : nums) {
			try {
				System.out.println(name + " Adding number " + num);
				buffer.put(num);

				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				System.out.println("Producer is interrupted");
			}
		}
		System.out.println(name + "Adding EOF and exiting ");
		try {
			buffer.put(Main.EOF);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

}

class MyConsumer implements Runnable {

	private ArrayBlockingQueue<String> buffer;
	private String name;

	public MyConsumer(ArrayBlockingQueue<String> buffer, String name) {
		this.buffer = buffer;
		this.name = name;
	}

	@Override
	public void run() {

		while (true) {
			synchronized(buffer) {
			try {
				if (buffer.isEmpty()) {
					continue;
				}

				if (buffer.peek().equals(Main.EOF)) {
					System.out.println("Exiting");
					break;
				} else {
					System.out.println(name + " Removed " + buffer.take());
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		}

	}

}
