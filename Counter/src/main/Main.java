package main;

public class Main {
	
	
	public static void main(String args[]) {
	
	Countdown countdown = new Countdown();
	
	CountDownThread thread1 = new CountDownThread(countdown);
	thread1.setName("Thread 1");
	CountDownThread thread2 = new CountDownThread(countdown);
	thread2.setName("Thread 2");
	thread1.start();
	thread2.start();
	
	
	}
	
	
	
	
	
	

}

class Countdown {
	
	private int i;
	
	public void countDown() {
		
		
		
		String name;
		
		switch(Thread.currentThread().getName()) {
		case "Thread 1" :
			name = " 1 ";
			break;
		case "Thread 2" :
			name = " 2 ";
			break;
		default :
			name= "Thread";
		}
		
		synchronized (this) {
		for( i = 10; i>0 ; i--) {
			System.out.println(name + Thread.currentThread().getName() + " i =" + i);
		}
		}
	}
}

class CountDownThread extends Thread {
	

	private Countdown threadCountDown;
	public CountDownThread(Countdown threadCountDown) {
		this.threadCountDown = threadCountDown;
	}

	@Override
	public void run() {
		threadCountDown.countDown();
	}
	
	
	
	
	
}
