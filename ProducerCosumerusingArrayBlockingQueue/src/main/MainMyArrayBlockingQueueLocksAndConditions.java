package main;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

public class MainMyArrayBlockingQueueLocksAndConditions {

	public static void main(String[] args) throws InterruptedException {
		
		MyBlockingQueueLocksAndConditions<Integer> queue = new MyBlockingQueueLocksAndConditions<Integer>(10);
		
		final Runnable Producer = new Runnable () {
			
			@Override
			public void run() {
				while (true) {
					Random random = new Random();
					try {
						int item = random.nextInt();
						queue.put(item);
						System.out.println("Item Produced +" +item);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		};
		
		final Runnable Consumer = new Runnable () {
			
			@Override
			public void run() {
				while(true) {
					try {
						int item = queue.take();
				    	System.out.println("Consumed Item" +item);	
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
				
			}
		};
		
		new Thread(Producer).start();
		new Thread(Producer).start();
	

		new Thread(Consumer).start();
		new Thread(Consumer).start();
		
		
		
		

	}

	
	
}
