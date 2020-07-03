import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {
	
	

	public static void main(String[] args) throws InterruptedException {
		
		
		CountDownLatch latch = new CountDownLatch(4);
				
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		for( int i = 0 ; i<4; i++) {
			service.execute(new Task(latch));;
		}
		
		latch.await();
		
		System.out.println(" Executed after all threads are completed execution");
		
		service.shutdown();

	}

}


class Task implements Runnable {
	
	
	private CountDownLatch latch;
	
	public Task(CountDownLatch latch) {
		this.latch = latch;
	}	

	@Override
	public void run() {
		System.out.println("Inside Run method ");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		latch.countDown();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   System.out.println("After latch is completed");
		
		
		
	}
	
}
