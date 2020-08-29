package interrupts;

public class Main {

	public static void main(String[] args) {
		Task t1 = new Task();
		t1.start();
		System.out.println("t1 thread is started");
		
		t1.interrupt();
		System.out.println("t1 thread is interrupted");

	}

}


class Task extends Thread {
	
	
	
	public void run () {
		
		try {
			
			Thread.sleep(6000);
			if(Thread.interrupted()) {
				System.out.println("Thread is interruped by main thread");
			}
		} catch (InterruptedException e) {
			System.out.println("Thread is interruped by main thread");
			
			e.printStackTrace();
		}
	}
}
