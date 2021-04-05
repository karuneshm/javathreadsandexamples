package threads.creation.example;

public class Main2 {

	public static void main(String[] args) {
		
		Thread newThread =  new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Hello From " + Thread.currentThread().getName());
				
			}
		});
		
		newThread.start();
		
		
		NewThread thread = new NewThread();
		thread.start();
		
		

	}
	
	private static class NewThread extends Thread {
		
		public void run() {
			System.out.println("Hello From " + Thread.currentThread().getName());
		}
		
	}
	
	
	

}
