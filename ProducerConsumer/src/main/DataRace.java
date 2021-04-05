package main;

public class DataRace {

	public static void main(String[] args) {
		SharedClass obj = new SharedClass();
		SharedThread sharedThread = new SharedThread(obj);
		SharedThread2 sharedThread2 = new SharedThread2(obj);
		sharedThread2.start();
		sharedThread.start();
		
		
		

	}
	
	private static class SharedThread extends Thread {
		
		private SharedClass obj;

		public SharedThread(SharedClass obj) {
			this.obj = obj;
		}

		@Override
		public void run() {
			for(int i = 0 ; i<Integer.MAX_VALUE;i++) {
			obj.increment();
			}
		}
		
		
	}
	
private static class SharedThread2 extends Thread {
		
		private SharedClass obj;

		public SharedThread2(SharedClass obj) {
			this.obj = obj;
		}

		@Override
		public void run() {
			for(int i = 0 ; i<Integer.MAX_VALUE;i++) {
				obj.checkForDataRace();
			}
			
			
		}
		
		
	}
	
	private static class SharedClass {
		private volatile int x = 0;
		private volatile int y = 0;
		
	
		
		public void increment() {
			x++;
			y++;
		}
		
		public void checkForDataRace()  {
			if(y>x) {
				System.out.println("y> x --> Data Race Detected");
			}
		}
		
		
	}

}
