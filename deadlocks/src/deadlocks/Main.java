package deadlocks;

public class Main {
	
	public static Object lock1 = new Object();
	public static Object lock2 = new Object();

	public static void main(String[] args) {
		
		new Thread1().start();
		new Thread2().start();
		//new Thread3().start();
	

	}
	
	private static class Thread1 extends Thread {
		
		public void run() {
			synchronized(lock1) {
				System.out.println("Thread 1 : has lock1");
				try {
					Thread.sleep(100);
				} catch(InterruptedException e) {
					
				}
				
				System.out.println("Thread 1 :waiting for lock2");
				synchronized(lock2) {
					System.out.println("Thread 1 :has lock1 lock2");
				}
				System.out.println("Thread 1: Released lock2");
			}
			
			System.out.println("Thread 1: Released lock1");

	}
	}
		private static class Thread2 extends Thread {
			public void run() {
				synchronized(lock2) {
					System.out.println("Thread 2 : has lock2");
					try {
						Thread.sleep(100);
					} catch(InterruptedException e) {
						
					}
					
					System.out.println("Thread 2 :waiting for lock1");
					synchronized(lock1) {
						System.out.println("Thread 2 :has lock2 lock1");
					}
					System.out.println("Thread 2: Released lock1");
				}
				
				System.out.println("Thread 2: Released lock2");	
			}
		}
		
		//Avoiding Deadlock
		//trylock
		private static class Thread3 extends Thread {
			public void run() {
				synchronized(lock1) {
					System.out.println("Thread 2 : has lock1");
					try {
						Thread.sleep(100);
					} catch(InterruptedException e) {
						
					}
					
					System.out.println("Thread 2 :waiting for lock2");
					synchronized(lock2) {
						System.out.println("Thread 2 :has lock2 lock1");
					}
					System.out.println("Thread 2: Released lock2");
				}
				
				System.out.println("Thread 2: Released lock1");	
			}
		}
}
	


