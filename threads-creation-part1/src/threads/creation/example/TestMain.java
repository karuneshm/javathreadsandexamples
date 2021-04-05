package threads.creation.example;

public class TestMain {

	

	public static void main(String[] args) throws InterruptedException {
	
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
			 System.out.println("We are in Thread " + Thread.currentThread().getName());
			 System.out.println("Prirotiy " + Thread.currentThread().getPriority());
			 throw new RuntimeException("Exception Occured");
				
			}
		});
		
		thread.setName("Worker Thread");
		
		thread.setPriority(Thread.MAX_PRIORITY);
		
		thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(t.getName() + " is thrown an exception " + e.getMessage());
				
			}
		});
		
		System.out.println("We are in Thread " +Thread.currentThread().getName() + " before starng the thread");
		thread.start();
		System.out.println("We are in Thread " +Thread.currentThread().getName() + " after starng the thread");
		
		//Thread.sleep(10000);

	}

}
