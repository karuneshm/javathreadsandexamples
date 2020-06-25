package threads;

public class AnotherThread extends Thread{

	@Override
	public void run() {
		System.out.println("hello from "+currentThread().getName()+" thread");
		
		try {
		Thread.sleep(3000);	
		} catch(InterruptedException e) {
			System.out.println("Interrupted Exception");
			return;
		}
		
	
		System.out.println("hello from "+currentThread().getName()+" thread is back again");
	}
	
	

}
