import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

	public static void main(String[] args) throws InterruptedException {
		
		CyclicBarrier barrier = new CyclicBarrier(4);
		
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		for (int i = 0; i<4; i++) {
			service.execute(new Task(barrier));
		}
		
		Thread.sleep(1000);
		System.out.println(" May be Executed after barrier wait for 4 threads");
		
		service.shutdown();
		
		
	}

}

class Task implements Runnable {
	
	private CyclicBarrier barrier;
	
	public Task(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Inside Run Method");
			try {
				Thread.sleep(1000);
				System.out.println("All Threads will wait here");
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Exceuted After All threads");
		}
		

	}
	
}
