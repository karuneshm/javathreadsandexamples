import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceImpl {

	public static void main(String[] args) {
		
		
		//ExecutorService  = Executors.newFixedThreadPool(10);
		//ExecutorService executorService = Executors.newCachedThreadPool();
		
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
		//executorService.schedule(new Task(), 1000, TimeUnit.MILLISECONDS);
		
		//executorService.scheduleAtFixedRate(new Task(), 2, 2, TimeUnit.SECONDS);
		
		//executorService.scheduleWithFixedDelay(new Task(), 2, 2, TimeUnit.SECONDS);
		
		
		
	
		
//		for( int i = 0; i<100 ;i ++) {
//			executorService.submit(new Task());
//		}
		
		System.out.println(" The Running Thread name is " + Thread.currentThread().getName());
		//executorService.shutdown();
		

	}

}

class Task implements Runnable {
	
	
	
	@Override
	public void run() {
		System.out.println(" The Running Thread name is  "  + Thread.currentThread().getName());
		
	}
}

