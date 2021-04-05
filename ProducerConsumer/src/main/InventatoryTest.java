package main;

public class InventatoryTest {

	public static void main(String[] args) throws InterruptedException {
		
		Inventory inventory = new Inventory();
		IncrementingThread incrementingThread = new IncrementingThread(inventory);
		DecrementingThread decrementingThread = new DecrementingThread(inventory);
		
		incrementingThread.start();
		
		decrementingThread.start();
		incrementingThread.join();
		decrementingThread.join();
		
		System.out.println("Available Items " +inventory.getItems());
		
		

	}
	
	private static class IncrementingThread extends Thread {

		private Inventory inventory;
		
	
		public IncrementingThread(Inventory inventory) {
			this.inventory = inventory;
		}



		@Override
		public void run() {
			for(int i = 0 ; i<10000; i++) {
				inventory.increment();
			}
			
		}
		
		
		
	}
	
	
	private static class DecrementingThread extends Thread {

		private Inventory inventory;
		
	
		public DecrementingThread(Inventory inventory) {
			this.inventory = inventory;
		}



		@Override
		public void run() {
			for(int i = 0; i<10000;i++) {
				inventory.decrement();
			}
			
		}
		
		
		
	}
	
	private static class Inventory {
		private int items = 0;
		
		public synchronized void  increment() {
			 items++;
		}
		
		public synchronized void decrement() {
			items--;
		}
		
		public synchronized int getItems() {
			return items;
		}
	}

}
