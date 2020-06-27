package main;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<Item> queue = new ArrayBlockingQueue<Item>(10);
		
		final Runnable Producer = new Runnable () {
			
			@Override
			public void run() {
				while (true) {
					Random random = new Random();
					Item item = new Item(random.nextInt());
					try {
						queue.put(item);
						System.out.println("Item Produced +" +item.getItem());
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
					Item item;
					try {
						item = queue.take();
				    	System.out.println("Consumed Item" + item.getItem());	
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
