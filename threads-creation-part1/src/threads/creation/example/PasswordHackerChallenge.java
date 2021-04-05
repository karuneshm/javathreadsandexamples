package threads.creation.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordHackerChallenge {
	
	public static final int MAX_PASSWORD= 9999;

	public static void main(String[] args) {
		Random random = new Random();
		
		Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
		List<Thread> threads = new ArrayList<Thread>();
		threads.add(new AscendingThread(vault));
		threads.add(new DescendingThread(vault));
		threads.add(new PoliceThread());
		
		for(int i = 0; i <threads.size(); i++ ) {
			threads.get(i).start();
		}
		
		
		

	}
	
	private static class Vault {
		private int password;
		
		public Vault(int password) {
			this.password = password;
		}
		
		public boolean isCorrectPassword(int guess) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return this.password == guess;
		}
	}

	private static abstract class HackerThread extends Thread {
		
		protected Vault vault;
		
		public HackerThread(Vault valut) {
			this.vault = valut;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(Thread.MAX_PRIORITY);
		}

		@Override
		public void start() {
			System.out.println("Starting Thread " + this.getName());
			super.start();
		}
		
		
		
	}
	
	private static class AscendingThread extends HackerThread {

		public AscendingThread(Vault vault) {
			super(vault);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			for(int guess = 0 ; guess <MAX_PASSWORD ;guess++) {
		     if(vault.isCorrectPassword(guess)) {
		    	 System.out.println("Ascending Thread guess the password " + guess);
		    	 System.exit(0);
		     }
			}
		}
			
	}
	
	private static class DescendingThread extends HackerThread {

		public DescendingThread(Vault valut) {
			super(valut);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void run() {
			for(int guess = MAX_PASSWORD ; guess > 0 ;guess--) {
		     if(vault.isCorrectPassword(guess)) {
		    	 System.out.println("Descending Thread guess the password " + guess);
		    	 System.exit(0);
		     }
			}
		}
		
	}
		
	private static class PoliceThread extends Thread {
		@Override
		public void run() {
			for (int i = 10; i > 0; i--) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(i);

			}
			
			System.out.println("Game Over For You");
			System.exit(0);
		}
	}
}



 
