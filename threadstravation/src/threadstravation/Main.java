package threadstravation;

public class Main {
	
	private static Object lock = new Object();

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new worker("Thread -1 "), "Priority 10");
		Thread t2 = new Thread(new worker("Thread -2 "), "Priority 8");
		Thread t3 = new Thread(new worker("Thread -3 "), "Priority 6");
		Thread t4 = new Thread(new worker("Thread -4 "), "Priority 4");
		Thread t5 = new Thread(new worker("Thread -5 "), "Priority 2");
		
		
		t1.setPriority(10);
		t2.setPriority(8);
		t3.setPriority(6);
		t4.setPriority(4);
		t5.setPriority(2);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		
		

	}
	
	private static class worker implements Runnable {
		
		private int runCount = 1;
		private String name;
	
		public worker(String name) {
			this.name = name;
		}




		@Override
		public void run() {
			for(int i = 0 ; i<100; i++) {
				synchronized(lock) {
					System.out.format(name+"%s: runcount =%d\n", Thread.currentThread().getName(),runCount++);
				}
			}
		}
		
	}

}
