package threads;

public class Main {

	public static void main(String[] args) {
		System.out.println("hello From main Threads");
		Thread anotherThread = new AnotherThread();
		anotherThread.start();
		anotherThread.setName("Karunesh");
		System.out.println(anotherThread.getName());
	    
		Thread.interrupted();
		
		Thread runnableThread = new Thread(new RunnableThread());
		runnableThread.start();
		
		
		Thread myRunnableThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Hello From Annoymous class of Run");
				try {
					anotherThread.join();
				} catch (InterruptedException e) {
					System.out.println("hello From Run Method ");
				}
				
			}
		});
		
	
		new Thread() {
			
			@Override
			public void run() {
				
				System.out.println("hello From Anonmyous Threads");
				
			}
			
		}.start();
		
		myRunnableThread.start();
		
		
		
		anotherThread.interrupt();
	}
	
	

}
